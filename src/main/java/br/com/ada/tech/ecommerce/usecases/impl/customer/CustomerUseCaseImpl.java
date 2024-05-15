package br.com.ada.tech.ecommerce.usecases.impl.customer;

import br.com.ada.tech.ecommerce.model.Customer;
import br.com.ada.tech.ecommerce.model.CustomerScore;
import br.com.ada.tech.ecommerce.usecases.INotifierUseCase;
import br.com.ada.tech.ecommerce.usecases.customer.ICustomerUseCase;
import br.com.ada.tech.ecommerce.usecases.customer.score.ISearchScoreUseCase;
import br.com.ada.tech.ecommerce.usecases.repository.ICustomerRepository;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerUseCaseImpl implements ICustomerUseCase {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    private ICustomerRepository repository;
    private ISearchScoreUseCase scoreUseCase;
    private INotifierUseCase<Customer> notifier;

    public CustomerUseCaseImpl(
            ICustomerRepository repository,
            ISearchScoreUseCase scoreUseCase,
            INotifierUseCase<Customer> notifier
    ) {
        this.repository = repository;
        this.scoreUseCase = scoreUseCase;
        this.notifier = notifier;
    }

    @Override
    @Transactional
    public void create(Customer customer) {
        findScore(customer)
                .map(CustomerScore::getScore)
                .ifPresent(customer::setScore);
        repository.save(customer);
        notifier.registered(customer);
    }

    @Override
    public List<Customer> list() {
        return repository.findAll();
    }

    @Override
    public Customer findByDocument(String document) {
        Customer found = null;
        if (document != null) {
            found = repository.findByDocument(document);
        }
        return found;
    }

    @Override
    public List<Customer> findByName(String name) {
        List<Customer> found = new ArrayList<>();
        if (name != null) {
            found = repository.findByName(name);
        }
        return found;
    }

    @Override
    @Transactional
    public void update(Customer customer) {
        repository.save(customer);
        notifier.updated(customer);
    }

    @Override
    @Transactional
    public Customer delete(Long id) {
        Customer customer = repository.findById(id).orElse(null);
        if (customer != null) {
            repository.delete(customer);
            notifier.deleted(customer);
        }
        return customer;
    }

    private Optional<CustomerScore> findScore(Customer customer) {
        try {
            return scoreUseCase.search(customer);
        } catch (Exception ex) {
            logger.error("Error on search score", ex);
            return Optional.of(CustomerScore.EMPTY);
        }
    }
}
