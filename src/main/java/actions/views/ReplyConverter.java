package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Reply;

/**
 * お客様の返信データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class ReplyConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param rv ReplyViewのインスタンス
     * @return Replyのインスタンス
     */
    public static Reply toModel(ReplyView rv) {
        return new Reply(
                rv.getId(),
                VoiceConverter.toModel(rv.getVoice()),
                CustomerConverter.toModel(rv.getCustomer()),
                rv.getReplyDate(),
                rv.getTitle(),
                rv.getContent(),
                rv.getCreatedAt());
                //vv.getUpdatedAt());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param r Replyのインスタンス
     * @return ReplyViewのインスタンス
     */
    public static ReplyView toView(Reply r) {

        if (r == null) {
            return null;
        }

        return new ReplyView(
                r.getId(),
                VoiceConverter.toView(r.getVoice()),
                CustomerConverter.toView(r.getCustomer()),
                r.getVoiceDate(),
                r.getTitle(),
                r.getContent(),
                r.getCreatedAt());
                //v.getUpdatedAt());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<ReplyView> toViewList(List<Reply> list) {
        List<ReplyView> evs = new ArrayList<>();

        for (Reply r : list) {
            evs.add(toView(r));
        }

        return evs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param r DTOモデル(コピー先)
     * @param rv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Reply r, ReplyView rv) {
        r.setId(rv.getId());
        r.setVoice(VoiceConverter.toModel(rv.getVoice()));
        r.setCustomer(CustomerConverter.toModel(rv.getCustomer()));
        r.setVoiceDate(rv.getReplyDate());
        r.setTitle(rv.getTitle());
        r.setContent(rv.getContent());
        r.setCreatedAt(rv.getCreatedAt());
        //v.setUpdatedAt(vv.getUpdatedAt());

    }

}