package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
//@ScriptAssert(lang="javascript", script="_this.price * _this.quantity >= 10000",
//        message="총합이 10000원을 넘지 않았습니다."
//)
//ScriptAssert.item, Script도 errordㅔ 넣을 수는 있다.
//이 기능 너무 단순함... Item말고 다른 거 써야하면 어ㅓㅎ게 할래?
//자바 코드로 쓰는 걸 개인적으로 권장함.
public class Item {

    private Long id;

    @NotBlank
    private String itemName;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private Integer price;

    @NotNull
    @Max(9999)
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
