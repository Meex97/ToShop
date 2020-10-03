package unito.progetto.esame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unito.progetto.esame.enums.PaymentMode;
import unito.progetto.esame.model.PaymentCallback;
import unito.progetto.esame.model.PaymentDetail;
import unito.progetto.esame.service.PaymentService;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // metodo che permette di salvare i dettagli di pagamento nel DB
    @PostMapping(path = "/payment-details")
    public @ResponseBody
    PaymentDetail proceedPayment(@RequestBody PaymentDetail paymentDetail){
       // PaymentCallback paymentCallback = new PaymentCallback();
        //paymentCallback.setMihpayid("4934545");
        //paymentCallback.setMode(PaymentMode.CC);
        return paymentService.proceedPayment(paymentDetail);
    }

    //meotodo di callback eseguito nel caso di pagamento avvenuto con successo o fallito
    @RequestMapping(path = "/payment-response", method = RequestMethod.POST)
    public @ResponseBody
    String payuCallback(@RequestParam String mihpayid, @RequestParam String status, @RequestParam PaymentMode mode, @RequestParam String txnid, @RequestParam String hash){
        PaymentCallback paymentCallback = new PaymentCallback();
        paymentCallback.setMihpayid(mihpayid);
        paymentCallback.setTxnid(txnid);
        paymentCallback.setMode(mode);
        paymentCallback.setHash(hash);
        paymentCallback.setStatus(status);
        return paymentService.payuCallback(paymentCallback);
    }
}
