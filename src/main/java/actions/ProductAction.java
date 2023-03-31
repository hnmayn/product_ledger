package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.ProductView;
import actions.views.UserView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import services.ProductService;

/**
 * 商品管理に関する処理を行うActionクラス
 *
 */
public class ProductAction extends ActionBase {

    private ProductService service;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new ProductService();

        // メソッドを実行
        invoke();
        service.close();
    }

    /**
     * 一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException {

        // 指定されたページ数の一覧画面に表示する商品データを取得
        int page = getPage();
        String keyword = getRequestParam(AttributeConst.PRD_KEYWORD);
        int deleteAllFlag = toNumber(getRequestParam(AttributeConst.PRD_DELETE_ALL));
        List<ProductView> products = service.getAllPerPage(page, keyword, deleteAllFlag);

        // 全商品データの件数を取得
        long productsCount = service.countAll(keyword, deleteAllFlag);

        putRequestScope(AttributeConst.PRODUCTS, products); // 取得した商品データ
        putRequestScope(AttributeConst.PRD_COUNT, productsCount); // 全ての商品データの件数
        putRequestScope(AttributeConst.PAGE, page); // ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); // 1ページに表示するレコードの数

        // セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope (AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        // 一覧画面を表示
        forward(ForwardConst.FW_PRD_INDEX);

    }

    /**
     * 新規登録画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void entryNew() throws ServletException, IOException {

        putRequestScope(AttributeConst.TOKEN, getTokenId()); // CSRF対策用トークン

        ProductView pv = new ProductView();
        putRequestScope(AttributeConst.PRODUCT, pv); // 商品インスタンス

        // 新規登録画面を表示
        forward(ForwardConst.FW_PRD_NEW);
    }
    /**
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {

        // CSRF対策 tokenのチェック
        if (checkToken()) {

            // セッションからログイン中の従業員情報を取得
            UserView uv = (UserView) getSessionScope(AttributeConst.LOGIN_USER);

            // パラメータの値をもとに商品情報のインスタンスを作成する
            ProductView pv = new ProductView(
                    null,
                    getRequestParam(AttributeConst.PRD_NAME),
                    getRequestParam(AttributeConst.PRD_PRC),
                    getRequestParam(AttributeConst.PRD_WIDTH),
                    getRequestParam(AttributeConst.PRD_DEPTH),
                    getRequestParam(AttributeConst.PRD_HEIGHT),
                    getRequestParam(AttributeConst.PRD_MTRL),
                    getRequestParam(AttributeConst.PRD_CONTENT),
                    getRequestParam(AttributeConst.PRD_QUANTITY),
                    toNumber(getRequestParam(AttributeConst.PRD_OBS_FLAG)),
                    uv, // ログインしている従業員を、日報作成者として登録する
                    null,
                    null);

            //商品情報登録
            List<String> errors = service.create(pv);

            if (errors.size() > 0) {
                // 登録中にエラーがあった場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); // CSRF対策用トークン
                putRequestScope(AttributeConst.PRODUCT, pv); // 入力された商品情報
                putRequestScope(AttributeConst.ERR, errors); // エラーのリスト

                // 新規登録画面を再表示
                forward(ForwardConst.FW_PRD_NEW);

            } else {
                // 登録中にエラーがなかった場合

                // セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                // 一覧画面にリダイレクト
                redirect(ForwardConst.ACT_PRD, ForwardConst.CMD_INDEX);
            }
        }
    }

    /**
     * 詳細画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void show() throws ServletException, IOException {

        // idを条件に商品データを取得する
        ProductView pv = service.findOne(toNumber(getRequestParam(AttributeConst.PRD_ID)));

        if(pv == null) {
            // 該当の商品データが存在しない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);
        } else {

            putRequestScope(AttributeConst.PRODUCT, pv); // 取得した商品データ

            // 詳細画面を表示
            forward(ForwardConst.FW_PRD_SHOW);
        }
    }

    /**
     * 編集画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void edit() throws ServletException, IOException {

        // idを条件に商品データを取得する
        ProductView pv = service.findOne(toNumber(getRequestParam(AttributeConst.PRD_ID)));

        // セッションからログイン中の従業員情報を取得
        UserView uv = (UserView) getSessionScope(AttributeConst.LOGIN_USER);

        if (pv == null || uv.getId() != pv.getUser().getId()) {
            // 該当の商品データが存在しない、または
            // ログインしている従業員が商品情報の作成者でない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);
        } else {

            putRequestScope(AttributeConst.TOKEN, getTokenId()); // CSRF対策用トークン
            putRequestScope(AttributeConst.PRODUCT, pv); // 取得した商品データ

            //編集画面を表示
            forward(ForwardConst.FW_PRD_EDIT);
        }
    }
    /*
    * 更新を行う
    * @throws ServletException
    * @throws IOException
    */
   public void update() throws ServletException, IOException {

       //CSRF対策 tokenのチェック
       if (checkToken()) {

           // idを条件に商品データを取得する
           ProductView pv = service.findOne(toNumber(getRequestParam(AttributeConst.PRD_ID)));

           //入力された商品情報を設定する
           pv.setName(getRequestParam(AttributeConst.PRD_NAME));
           pv.setPrice(getRequestParam(AttributeConst.PRD_PRC));
           pv.setWidth(getRequestParam(AttributeConst.PRD_WIDTH));
           pv.setDepth(getRequestParam(AttributeConst.PRD_DEPTH));
           pv.setHeight(getRequestParam(AttributeConst.PRD_HEIGHT));
           pv.setMaterial(getRequestParam(AttributeConst.PRD_MTRL));
           pv.setContent(getRequestParam(AttributeConst.PRD_CONTENT));
           pv.setQuantity(getRequestParam(AttributeConst.PRD_QUANTITY));
           //pv.setObsoleteFlag(toNumber(getRequestParam(AttributeConst.PRD_OBS_FLAG)));

           // 商品データを更新する
           List<String> errors = service.update(pv);

           if (errors.size() > 0) {
               // 更新中にエラーが発生した場合

               putRequestScope(AttributeConst.TOKEN, getTokenId()); // CSRF対策用トークン
               putRequestScope(AttributeConst.PRODUCT, pv); // 入力された商品情報
               putRequestScope(AttributeConst.ERR, errors); // エラーのリスト

               // 編集画面を再表示
               forward(ForwardConst.FW_PRD_EDIT);
           } else {
               // 更新中にエラーがなかった場合

               // セッションに更新完了のフラッシュメッセージを設定
               putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

               // 一覧画面にリダイレクト
               redirect(ForwardConst.ACT_PRD, ForwardConst.CMD_INDEX);
           }
       }
   }

    /**
     * 論理削除を行う
     * @throws ServletException
     * @throws IOException
     */
    public void destroy() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) {

            //idを条件に在庫データを論理削除する
            service.destroy(toNumber(getRequestParam(AttributeConst.PRD_ID)));

            //セッションに削除完了のフラッシュメッセージを設定
            putSessionScope(AttributeConst.FLUSH, MessageConst.P_DELETED.getMessage());

            //一覧画面にリダイレクト
            redirect(ForwardConst.ACT_PRD, ForwardConst.CMD_INDEX);
        }
    }
}
