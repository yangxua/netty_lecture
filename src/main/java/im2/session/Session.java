package im2.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Auther: allanyang
 * @Date: 2019/5/27 19:49
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Session {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

}