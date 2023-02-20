package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.CustomerConverter;
import actions.views.CustomerView;
import actions.views.VoiceConverter;
import actions.views.VoiceView;
import constants.JpaConst;
import models.Voice;
import models.validators.VoiceValidator;

/**
 * お客様の声テーブルの操作に関わる処理を行うクラス
 */
public class VoiceService extends ServiceBase {

    /**
     * 指定したお客様が作成した声データを、指定されたページ数の一覧画面に表示する分取得しVoiceViewのリストで返却する
     * @param customer お客様
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<VoiceView> getMinePerPage(CustomerView customer, int page) {

        List<Voice> voices = em.createNamedQuery(JpaConst.Q_VOI_GET_ALL_MINE, Voice.class)
                .setParameter(JpaConst.JPQL_PARM_CUSTOMER, CustomerConverter.toModel(customer))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return VoiceConverter.toViewList(voices);
    }

    /**
     * 指定したお客様が作成した声データの件数を取得し、返却する
     * @param customer
     * @return 声データの件数
     */
    public long countAllMine(CustomerView customer) {

        long count = (long) em.createNamedQuery(JpaConst.Q_VOI_COUNT_ALL_MINE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_CUSTOMER, CustomerConverter.toModel(customer))
                .getSingleResult();

        return count;
    }

    /**
     * 指定されたページ数の一覧画面に表示する声データを取得し、VoiceViewのリストで返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<VoiceView> getAllPerPage(int page) {

        List<Voice> voices = em.createNamedQuery(JpaConst.Q_VOI_GET_ALL, Voice.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return VoiceConverter.toViewList(voices);
    }

    /**
     * 声テーブルのデータの件数を取得し、返却する
     * @return データの件数
     */
    public long countAll() {
        long voices_count = (long) em.createNamedQuery(JpaConst.Q_VOI_COUNT, Long.class)
                .getSingleResult();
        return voices_count;
    }

    /**
     * idを条件に取得したデータをVoiceViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public VoiceView findOne(int id) {
        return VoiceConverter.toView(findOneInternal(id));
    }

    /**
     * 画面から入力された声の登録内容を元にデータを1件作成し、声テーブルに登録する
     * @param vv 声の登録内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> create(VoiceView vv) {
        List<String> errors = VoiceValidator.validate(vv);
        if (errors.size() == 0) {
            LocalDateTime ldt = LocalDateTime.now();
            vv.setCreatedAt(ldt);
            //vv.setUpdatedAt(ldt);
            createInternal(vv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * 画面から入力された声の登録内容を元に、声データを更新する
     * @param vv 声の更新内容
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
    private Voice findOneInternal(int id) {
        return em.find(Voice.class, id);
    }

    /**
     * 声データを1件登録する
     * @param vv 声データ
     */
    private void createInternal(VoiceView vv) {

        em.getTransaction().begin();
        em.persist(VoiceConverter.toModel(vv));
        em.getTransaction().commit();

    }

    /**
     * 声データを更新する
     * @param vv 声データ
     */
    //private void updateInternal(VoiceView vv) {

        //em.getTransaction().begin();
        //Voice v = findOneInternal(vv.getId());
        //VoiceConverter.copyViewToModel(v, vv);
        //em.getTransaction().commit();

    //}






}