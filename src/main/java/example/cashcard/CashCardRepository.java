package example.cashcard;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.Optional;


interface CashCardRepository extends CrudRepository<CashCard, Long>, PagingAndSortingRepository<CashCard, Long> {

	// Spring Data will take care of the actual implementations
	// (writing the SQL queries) for us.
	Optional<CashCard> findByIdAndOwner(Long id, String owner);
	Page<CashCard> findByOwner(String owner, PageRequest pageRequest);
	boolean existsByIdAndOwner(Long id, String owner);

}
