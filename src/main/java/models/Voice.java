package models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * お客様の声データのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_VOI)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_VOI_GET_ALL,
            query = JpaConst.Q_VOI_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_VOI_COUNT,
            query = JpaConst.Q_VOI_COUNT_DEF),
    @NamedQuery(
            name = JpaConst.Q_VOI_GET_ALL_MINE,
            query = JpaConst.Q_VOI_GET_ALL_MINE_DEF),
    @NamedQuery(
            name = JpaConst.Q_VOI_COUNT_ALL_MINE,
            query = JpaConst.Q_VOI_COUNT_ALL_MINE_DEF)
})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class Voice {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.VOI_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * お客様の声を登録したお客様
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.VOI_COL_CUS, nullable = false)
    private Customer customer;

    /**
     * いつの声かを示す日付
     */
    @Column(name = JpaConst.VOI_COL_VOI_DATE, nullable = false)
    private LocalDate voiceDate;

    /**
     * 声のタイトル
     */
    @Column(name = JpaConst.VOI_COL_TITLE, length = 255, nullable = false)
    private String title;

    /**
     * 声の内容
     */
    @Lob
    @Column(name = JpaConst.VOI_COL_CONTENT, nullable = false)
    private String content;

    /**
     * 登録日時
     */
    @Column(name = JpaConst.VOI_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    //@Column(name = JpaConst.VOI_COL_UPDATED_AT, nullable = false)
    //private LocalDateTime updatedAt;

}