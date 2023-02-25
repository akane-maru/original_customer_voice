package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.CustomerConverter;
import actions.views.CustomerView;
import actions.views.ReplyConverter;
import actions.views.ReplyView;
import constants.JpaConst;
import models.Reply;
import models.validators.ReplyValidator;

/**
 * お客様の声テーブルの操作に関わる処理を行うクラス
 */
public class ReplyService extends ServiceBase {

    /**
     * 指定した従業員が作成した返信データを、指定されたページ数の一覧画面に表示する分取得しReplyViewのリストで返却する
     * @param customer 従業員
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<ReplyView> getMinePerPage(CustomerView customer, int page) {

        List<Reply> replys = em.createNamedQuery(JpaConst.Q_REP_GET_ALL_MINE, Reply.class)
                .setParameter(JpaConst.JPQL_PARM_CUSTOMER, CustomerConverter.toModel(customer))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return ReplyConverter.toViewList(replys);
    }

    /**
     * 指定した従業員が作成した返信データの件数を取得し、返却する
     * @param customer
     * @return 返信データの件数
     */
    public long countAllMine(CustomerView customer) {

        long count = (long) em.createNamedQuery(JpaConst.Q_REP_COUNT_ALL_MINE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_CUSTOMER, CustomerConverter.toModel(customer))
                .getSingleResult();

        return count;
    }

    /**
     * 指定されたページ数の一覧画面に表示する返信データを取得し、ReplyViewのリストで返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<ReplyView> getAllPerPage(int page) {

        List<Reply> replys = em.createNamedQuery(JpaConst.Q_REP_GET_ALL, Reply.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return ReplyConverter.toViewList(replys);
    }

    /**
     * 返信テーブルのデータの件数を取得し、返却する
     * @return データの件数
     */
    public long countAll() {
        long replys_count = (long) em.createNamedQuery(JpaConst.Q_REP_COUNT, Long.class)
                .getSingleResult();
        return replys_count;
    }

    /**
     * idを条件に取得したデータをReplyViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public ReplyView findOne(int id) {
        return ReplyConverter.toView(findOneInternal(id));
    }

    /**
     * 画面から入力された返信の登録内容を元にデータを1件作成し、返信テーブルに登録する
     * @param rv 返信の登録内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> create(ReplyView rv) {
        List<String> errors = ReplyValidator.validate(rv);
        if (errors.size() == 0) {
            LocalDateTime ldt = LocalDateTime.now();
            rv.setCreatedAt(ldt);
            //vv.setUpdatedAt(ldt);
            createInternal(rv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * 画面から入力された返信の登録内容を元に、返信データを更新する
     * @param rv 返信の更新内容
     * @return バリデーションで発生したエラーのリスト
     */
    //public List<String> update(VoiceView vv) {

        //バリデーションを行う
        //List<String> errors = VoiceValidator.validate(vv);

        //if (errors.size() == 0) {

            //更新日時を現在時刻に設定
            //LocalDateTime ldt = LocalDateTime.now();
            //rv.setUpdatedAt(ldt);

            //updateInternal(vv);
        //}

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        //return errors;
    //}

    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private Reply findOneInternal(int id) {
        return em.find(Reply.class, id);
    }

    /**
     * 返信データを1件登録する
     * @param rv 返信データ
     */
    private void createInternal(ReplyView rv) {

        em.getTransaction().begin();
        em.persist(ReplyConverter.toModel(rv));

        em.getTransaction().commit();

    }

    /**
     * 返信データを更新する
     * @param rv 返信データ
     */
    //private void updateInternal(VoiceView vv) {

        //em.getTransaction().begin();
        //Voice v = findOneInternal(vv.getId());
        //VoiceConverter.copyViewToModel(v, vv);
        //em.getTransaction().commit();

    //}

}