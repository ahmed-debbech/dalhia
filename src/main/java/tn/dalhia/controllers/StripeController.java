package tn.dalhia.controllers;


import com.google.gson.annotations.SerializedName;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StripeController {

    static class CreatePayment{
        @SerializedName("items")
        Object[] items;
        public Object[] getItems(){
            return items;
        }
    }
    static class CreatePaymentResponse{
        private String clientSecret;
        public CreatePaymentResponse(String clientSecret){
            this.clientSecret = clientSecret;
        }
    }

    @Value("${stripe.apikey}")
    String stripeKey;



    @PostMapping("stripe/create-payment")
    public CreatePaymentResponse CreatePaymentIntent(@RequestBody CreatePayment createPayment) throws StripeException {
        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setCurrency("usd")
                .setAmount(15*100L)
                .build();
        PaymentIntent intent = PaymentIntent.create(createParams);
        return new CreatePaymentResponse(intent.getClientSecret());

    }
    @RequestMapping("/stripe")
    public String index(){
        return "test: "+stripeKey;
    }

}
