package constants;

/**
 * DB関連の項目値を定義するインターフェース
 * ※インターフェイスに定義した変数は public static final 修飾子がついているとみなされる
 */
public interface JpaConst {

    //persistence-unit名
    String PERSISTENCE_UNIT_NAME = "original_customer_voice";

    //データ取得件数の最大値
    int ROW_PER_PAGE = 15; //1ページに表示するレコードの数

    //従業員テーブル
    String TABLE_CUS = "customers"; //テーブル名
    //従業員テーブルカラム
    String CUS_COL_ID = "id"; //id
    String CUS_COL_CODE = "code"; //お客様番号
    String CUS_COL_NAME = "name"; //氏名
    String CUS_COL_PASS = "password"; //パスワード
    String CUS_COL_ADMIN_FLAG = "admin_flag"; //管理者権限
    String CUS_COL_CREATED_AT = "created_at"; //登録日時
    String CUS_COL_UPDATED_AT = "updated_at"; //更新日時
    String CUS_COL_DELETE_FLAG = "delete_flag"; //削除フラグ

    int ROLE_ADMIN = 1; //管理者権限ON(管理者)
    int ROLE_GENERAL = 0; //管理者権限OFF(一般)
    int EMP_DEL_TRUE = 1; //削除フラグON(削除済み)
    int EMP_DEL_FALSE = 0; //削除フラグOFF(現役)

    //日報テーブル
    String TABLE_VOI = "voices"; //テーブル名
    //日報テーブルカラム
    String VOI_COL_ID = "id"; //id
    String VOI_COL_CUS = "customer_id"; //日報を作成したお客様のid
    String VOI_COL_REP_DATE = "voice_date"; //いつのお客様の声かを示す日付
    String VOI_COL_TITLE = "title"; //お客様の声のタイトル
    String VOI_COL_CONTENT = "content"; //お客様の声の内容
    String VOI_COL_CREATED_AT = "created_at"; //登録日時
    String VOI_COL_UPDATED_AT = "updated_at"; //更新日時

    //Entity名
    String ENTITY_CUS = "customer"; //お客様
    String ENTITY_VOI = "voice"; //お客様の声

    //JPQL内パラメータ
    String JPQL_PARM_CODE = "code"; //お客様番号
    String JPQL_PARM_PASSWORD = "password"; //パスワード
    String JPQL_PARM_CUSTOMER = "customer"; //お客様

    //NamedQueryの nameとquery
    //全てのお客様をidの降順に取得する
    String Q_CUS_GET_ALL = ENTITY_CUS + ".getAll"; //name
    String Q_CUS_GET_ALL_DEF = "SELECT e FROM Customer AS e ORDER BY e.id DESC"; //query
    //全てのお客様の件数を取得する
    String Q_CUS_COUNT = ENTITY_CUS + ".count";
    String Q_CUS_COUNT_DEF = "SELECT COUNT(e) FROM Customer AS e";
    //お客様番号とハッシュ化済パスワードを条件に未削除のお客様を取得する
    String Q_CUS_GET_BY_CODE_AND_PASS = ENTITY_CUS + ".getByCodeAndPass";
    String Q_CUS_GET_BY_CODE_AND_PASS_DEF = "SELECT e FROM Customer AS e WHERE e.deleteFlag = 0 AND e.code = :" + JPQL_PARM_CODE + " AND e.password = :" + JPQL_PARM_PASSWORD;
    //指定したお客様番号を保持するお客様の件数を取得する
    String Q_CUS_COUNT_REGISTERED_BY_CODE = ENTITY_CUS + ".countRegisteredByCode";
    String Q_CUS_COUNT_REGISTERED_BY_CODE_DEF = "SELECT COUNT(e) FROM Customer AS e WHERE e.code = :" + JPQL_PARM_CODE;
    //全てのお客様の声をidの降順に取得する
    String Q_VOI_GET_ALL = ENTITY_VOI + ".getAll";
    String Q_VOI_GET_ALL_DEF = "SELECT r FROM Voice AS r ORDER BY r.id DESC";
    //全てのお客様の声の件数を取得する
    String Q_VOI_COUNT = ENTITY_VOI + ".count";
    String Q_VOI_COUNT_DEF = "SELECT COUNT(r) FROM Voice AS r";
    //指定したお客様が作成したお客様の声を全件idの降順で取得する
    String Q_VOI_GET_ALL_MINE = ENTITY_VOI + ".getAllMine";
    String Q_VOI_GET_ALL_MINE_DEF = "SELECT r FROM Voice AS r WHERE r.customer = :" + JPQL_PARM_CUSTOMER + " ORDER BY r.id DESC";
    //指定したお客様が作成したお客様の声の件数を取得する
    String Q_VOI_COUNT_ALL_MINE = ENTITY_CUS + ".countAllMine";
    String Q_VOI_COUNT_ALL_MINE_DEF = "SELECT COUNT(r) FROM Voice AS r WHERE r.customer = :" + JPQL_PARM_CUSTOMER;


}
