package mk.ukim.finki.emk.shop.repository;

import mk.ukim.finki.emk.shop.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Nadica-PC on 7/21/2015.
 */
public interface LanguageRepository  extends JpaRepository<Language, Long>,
        JpaSpecificationExecutor<Language> {

}
