package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
        //item == clazz
        //item == subItem isAssignableFrom과 같음
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;
        //검증 로직
        if(!StringUtils.hasText(item.getItemName())){
            errors.rejectValue("itemName", "required");
            //이러면 끝. 어!? 그러면 item.itemName 은!? 바로바로 규칙이 있습니다. 왕...
            // 까보면 그냥 addError했던 거 다 해줌.
//          leve1, level2->  new String[]{"required.item.itemName", "required"}
            // MessageCodesResolver 기능 지원!
        }
//      ValidationUtils.rejectIfEmptyOrWhitespace(errors,"itemName","required");
//      위에꺼랑 똑같은 거야. 공백이다.값이 안들어갔다. 그런 거 체크할 때 쓸 수 있음. empty, 공백같은 단순 기능만 제공


        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000){
            errors.rejectValue("price","range", new Object[]{1000, 1000000}, null);
        }
        if(item.getQuantity() == null || item.getQuantity() >= 9999){
            errors.rejectValue("quantity","max", new Object[]{9999}, null);
        }
        //특정 필드가 아닌 복합 룰 검증
        if(item.getPrice() != null && item.getQuantity() != null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
                errors.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }
    }
}
