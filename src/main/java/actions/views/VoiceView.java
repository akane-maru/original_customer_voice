package actions.views;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * お客様の声情報について画面の入力値・出力値を扱うViewモデル
 *
 */
@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
public class VoiceView {

    /**
     * id
     */
    private Integer id;

    /**
     * 声を登録したお客様
     */
    private CustomerView customer;

    /**
     * いつの声かを示す日付
     */
    private LocalDate reportDate;

    /**
     * 声のタイトル
     */
    private String title;

    /**
     * 声の内容
     */
    private String content;

    /**
     * 登録日時
     */
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    //private LocalDateTime updatedAt;
}
