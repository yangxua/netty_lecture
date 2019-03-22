package im.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Auther: allanyang
 * @Date: 2019/3/22 15:33
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Session {

    private String userName;
    private String userId;
}