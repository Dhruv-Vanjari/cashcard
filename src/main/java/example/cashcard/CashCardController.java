package example.cashcard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;
import java.net.URI;

// @RestController tells Spring that this class is a Component of type RestController and
// capable of handling HTTP requests.
// @RequestMapping is a companion to @RestController that indicates which address requests
// must have to access this Controller.
@RestController
@RequestMapping("/cashcards")
class CashCardController {

   private final CashCardRepository cashCardRepository;

   private CashCardController(CashCardRepository cashCardRepository) {
      this.cashCardRepository = cashCardRepository;
   }

   @GetMapping("/{requestedId}")
   private ResponseEntity<CashCard> findById(@PathVariable Long requestedId) {

      Optional<CashCard> cashCard = cashCardRepository.findById(requestedId);

      if (cashCard.isPresent()) {
         return ResponseEntity.ok(cashCard.get());
      } else {
         return ResponseEntity.notFound().build();
      }
   }

   @PostMapping
   private ResponseEntity<Void> createCashCard(@RequestBody CashCard newCashCardRequest, UriComponentsBuilder ucb) {

      CashCard newCashCard = cashCardRepository.save(newCashCardRequest);
      URI newCashCardUri = ucb.path("cashcards/{id}").buildAndExpand(newCashCard.id()).toUri();

      return ResponseEntity.created(newCashCardUri).build();
   }

}
