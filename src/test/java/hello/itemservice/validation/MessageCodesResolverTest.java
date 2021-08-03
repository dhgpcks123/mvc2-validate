package hello.itemservice.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.validation.*;

public class MessageCodesResolverTest {

    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void messageCodesResolverObject() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
            Assertions.assertThat(messageCodes).containsExactly("required.item", "required");
        }

//        new ObjectError("item",new String[]{"required.item","required"})
    }
    
    
    @Test
    void messageCodesResolverField(){
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);
        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
            Assertions.assertThat(messageCodes).containsExactly(
                    "required.item.itemName",
                    "required.itemName",
                    "required.java.lang.String",
                    "required"
            );
        }


//        bindingResult.rejectValue("itemName","required")
        //얘가 codesResolver.resovleMessageCodes를 부른다.
//        new FieldError("item","itemName",null,false,null,null,null);

        /*
        messageCode = required.item.itemName
        messageCode = required.itemName
        messageCode = required.java.lang.String
        messageCode = required
         */
    }
}
