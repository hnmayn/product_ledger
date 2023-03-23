package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.ProductConverter;
import actions.views.ProductView;
import constants.JpaConst;
import models.Product;
import models.validators.ProductValidator;

/**
 * 商品テーブルの操作に関わる処理を行うクラス
 */
public class ProductService extends ServiceBase {

    /**
     * 指定した従業員が作成した商品データを、指定されたページ数の一覧画面に表示する分取得しProductViewのリストで返却する
     * @param employee 従業員
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<ProductView> getMinePerPage(EmployeeView employee, int page){

        List<Product> products = em.createNamedQuery(JpaConst.Q_PRD_GET_ALL_MINE,Product.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return ProductConverter.toViewList(products);
    }

    /**
     * 指定した従業員が作成した商品データの件数を取得し、返却する
     * @param employee
     * @return 商品データの件数
     */
    public long countAllMine(EmployeeView employee) {

        long count = (long) em.createNamedQuery(JpaConst.Q_PRD_COUNT_ALL_MINE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .getSingleResult();

        return count;
    }

    /**
     * 指定されたページ数の一覧画面に表示する商品データを取得し、ProductViewのリストで返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<ProductView> getAllPerPage(int page){

        List<Product> products = em.createNamedQuery(JpaConst.Q_PRD_GET_ALL, Product.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return ProductConverter.toViewList(products);
    }

    /**
     * 商品テーブルのデータの件数を取得し、返却する
     * @return データの件数
     */
    public long countAll() {
        long products_count = (long) em.createNamedQuery(JpaConst.Q_PRD_COUNT, Long.class)
                .getSingleResult();
        return products_count;
    }

    /**
     * idを条件に取得したデータをProductViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public ProductView findOne(int id) {
        return ProductConverter.toView(findOneInternal(id));
    }

    /**
     * 画面から入力された商品の登録内容を元にデータを1件作成し、商品テーブルに登録する
     * @param pv 商品の登録内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> create(ProductView pv) {
        List<String> errors = ProductValidator.validate(pv);
        if (errors.size() == 0) {
            LocalDateTime ldt = LocalDateTime.now();
            pv.setCreatedAt(ldt);
            pv.setUpdatedAt(ldt);
            createInternal(pv);
        }

        // バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * 画面から入力された商品の登録内容を元に、商品データを更新する
     * @param pv 商品の更新内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> update(ProductView pv) {

        // バリデーションを行う
        List<String> errors = ProductValidator.validate(pv);

        if (errors.size() == 0) {

            //更新日時を現在時刻に設定
            LocalDateTime ldt = LocalDateTime.now();
            pv.setUpdatedAt(ldt);

            updateInternal(pv);
        }

        // バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }
    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private Product findOneInternal(int id) {
        return em.find(Product.class, id);
    }

    /**
     * 商品データを1件登録する
     * @param pv 商品データ
     */
    private void createInternal(ProductView pv) {

        em.getTransaction().begin();
        em.persist(ProductConverter.toModel(pv));
        em.getTransaction().commit();
    }

    /**
     * 商品データを更新する
     * @param pv 商品データ
     */
    private void updateInternal(ProductView pv) {

        em.getTransaction().begin();
        Product p = findOneInternal(pv.getId());
        ProductConverter.copyViewToModel(p, pv);
        em.getTransaction().commit();
    }

    /**
     * idを条件に在庫データを論理削除する
     * @param id
     */
    public void destroy(Integer id) {

        //idを条件に登録済みの商品情報を取得する
        ProductView savedPrd = findOne(id);

        //更新日時に現在時刻を設定する
        LocalDateTime today = LocalDateTime.now();
        savedPrd.setUpdatedAt(today);

        //論理削除フラグをたてる
        savedPrd.setObsoleteFlag(JpaConst.PRD_OBS_TRUE);

        //更新処理を行う
        update(savedPrd);
    }
}
