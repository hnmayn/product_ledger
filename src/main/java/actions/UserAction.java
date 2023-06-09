package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.UserView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import constants.PropertyConst;
import services.UserService;

/**
 * ユーザーに関わる処理を行うActionクラス
 *
 */

public class UserAction extends ActionBase{

    private UserService service;

    /**
     * メソッドを実行する
     */

    @Override
    public void process() throws ServletException, IOException{

        service = new UserService();

      //メソッドを実行
        invoke();

        service.close();
    }

    /**
     * 一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException{

      // 管理者かどうかのチェック
        if (checkAdmin()) {

        // 指定されたページ数の一覧画面に表示するデータを取得
        int page = getPage();
        List<UserView> users = service.getPerPage(page);

        // 全てのユーザーデータの件数を取得
        long usersCount = service.countAll();

        putRequestScope(AttributeConst.USERS, users); // 取得したユーザーデータ
        putRequestScope(AttributeConst.USER_COUNT, usersCount); // 全てのユーザーデータ
        putRequestScope(AttributeConst.PAGE,page); // ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        // セッションにフラッシュメッセージが設定されている場合はリクエストスコープに差し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

      //一覧画面を表示
        forward(ForwardConst.FW_USER_INDEX);
    }
}

    /**
     * 新規登録画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void entryNew() throws ServletException, IOException {

      // 管理者かどうかのチェック
        if (checkAdmin()) {
            putRequestScope(AttributeConst.TOKEN, getTokenId()); // CSRF対策用トークン
            putRequestScope(AttributeConst.USER, new UserView()); // 空のユーザーインスタンス

      //新規登録画面を表示
            forward(ForwardConst.FW_USER_NEW);
    }
    }
    /**
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {

        // CSRF対策 tokenのチェック
        if (checkAdmin() && checkToken()) {

            // パラメータの値を元にユーザー情報のインスタンスを作成する
            UserView uv = new UserView(
                    null,
                    getRequestParam(AttributeConst.USER_CODE),
                    getRequestParam(AttributeConst.USER_NAME),
                    getRequestParam(AttributeConst.USER_DEPARTMENT),
                    getRequestParam(AttributeConst.USER_PASS),
                    toNumber(getRequestParam(AttributeConst.USER_ADMIN_FLG)),
                    null,
                    null,
                    AttributeConst.DEL_FLAG_FALSE.getIntegerValue());

            //アプリケーションスコープからpepper文字列を取得
            String pepper = getContextScope(PropertyConst.PEPPER);

            //ユーザー情報登録
            List<String> errors = service.create(uv, pepper);

            if (errors.size() > 0) {
                //登録中にエラーがあった場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.USER, uv); //入力されたユーザー情報
                putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

                //新規登録画面を再表示
                forward(ForwardConst.FW_USER_NEW);

            } else {
                //登録中にエラーがなかった場合

                //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_USER, ForwardConst.CMD_INDEX);
            }
        }
    }

    /**
     * 詳細画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void show() throws ServletException, IOException{

      //管理者かどうかのチェック
        if (checkAdmin()) {
      //idを条件にユーザーデータを取得する
        UserView uv = service.findOne(toNumber(getRequestParam(AttributeConst.USER_ID)));

        if (uv == null || uv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {

          //データが取得できなかった、または論理削除されている場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);
            return;
        }

        putRequestScope(AttributeConst.USER, uv); //取得したユーザー情報

      //詳細画面を表示
        forward(ForwardConst.FW_USER_SHOW);
    }
    }

    /**
     * 編集画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void edit() throws ServletException, IOException {

      //管理者かどうかのチェック
        if (checkAdmin()) {

      //idを条件にユーザーデータを取得する
        UserView uv = service.findOne(toNumber(getRequestParam(AttributeConst.USER_ID)));

        if (uv == null || uv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {

          //データが取得できなかった、または論理削除されている場合はエラー画面を表示

            forward(ForwardConst.FW_ERR_UNKNOWN);
            return;
        }

        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
        putRequestScope(AttributeConst.USER, uv); //取得したユーザー情報

        //編集画面を表示する
        forward(ForwardConst.FW_USER_EDIT);

    }
    }


    /**
     * 更新を行う
     * @throws ServletException
     * @throws IOException
     */
    public void update() throws ServletException, IOException {

      //CSRF対策 tokenのチェック
        if (checkAdmin() && checkToken()) {
           //パラメータの値を元にユーザー情報のインスタンスを作成する
            UserView uv = new UserView(

                    toNumber(getRequestParam(AttributeConst.USER_ID)),
                    getRequestParam(AttributeConst.USER_CODE),
                    getRequestParam(AttributeConst.USER_NAME),
                    getRequestParam(AttributeConst.USER_DEPARTMENT),
                    getRequestParam(AttributeConst.USER_PASS),
                    toNumber(getRequestParam(AttributeConst.USER_ADMIN_FLG)),
                    null,
                    null,
                    AttributeConst.DEL_FLAG_FALSE.getIntegerValue());

          //アプリケーションスコープからpepper文字列を取得
            String pepper = getContextScope(PropertyConst.PEPPER);

          //ユーザー情報更新
            List<String> errors = service.update(uv, pepper);

            if (errors.size() > 0) {
                //更新中にエラーが発生した場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.USER, uv); //入力されたユーザー情報
                putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

              //編集画面を再表示
                forward(ForwardConst.FW_USER_EDIT);
            } else {
                //更新中にエラーがなかった場合

                //セッションに更新完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_USER, ForwardConst.CMD_INDEX);
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
        if (checkAdmin() && checkToken()) {

            //idを条件にユーザーデータを論理削除する
            service.destroy(toNumber(getRequestParam(AttributeConst.USER_ID)));

            //セッションに削除完了のフラッシュメッセージを設定
            putSessionScope(AttributeConst.FLUSH, MessageConst.I_DELETED.getMessage());

            //一覧画面にリダイレクト
            redirect(ForwardConst.ACT_USER, ForwardConst.CMD_INDEX);
        }
    }
    /**
     * ログイン中のユーザーが管理者かどうかチェックし、管理者でなければエラー画面を表示
     * true: 管理者 false: 管理者ではない
     * @throws ServletException
     * @throws IOException
     */
    private boolean checkAdmin() throws ServletException, IOException {

      //セッションからログイン中のユーザー情報を取得
        UserView uv = (UserView) getSessionScope(AttributeConst.LOGIN_USER);

      // 管理者でなければエラー画面を表示
        if (uv.getAdminFlag() != AttributeConst.ROLE_ADMIN.getIntegerValue()){

            forward(ForwardConst.FW_ERR_UNKNOWN);
            return false;
        } else {
            return true;
        }
    }
}

