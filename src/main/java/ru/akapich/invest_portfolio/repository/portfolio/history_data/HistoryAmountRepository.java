package ru.akapich.invest_portfolio.repository.portfolio.history_data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.akapich.invest_portfolio.model.forms.sql.FormPurchaseDate;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;
import ru.akapich.invest_portfolio.model.portfolio.history_data.HistoryAmount;
import ru.akapich.invest_portfolio.model.forms.sql.FormDatePriceGraphSQLQuery;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * JavaBean object that interaction with Database.
 *
 * @author Aleksandr Marakulin
 **/

@Repository
public interface HistoryAmountRepository extends JpaRepository<HistoryAmount, Long> {

	@Query("SELECT DISTINCT h.ownedFinancialAsset FROM HistoryAmount h")
	List<OwnedFinancialAsset> findAllUniqueOwnedAssets();

	@Query("SELECT a FROM HistoryAmount a WHERE a.id = (" +
			"SELECT MAX(a2.id) FROM HistoryAmount a2 WHERE a2.ownedFinancialAsset = ?1) ")
	HistoryAmount lastAmountByOwnedFinancialAsset(OwnedFinancialAsset ownedFinancialAsset);

	@Query("SELECT SUM (p.total) FROM HistoryAmount p, OwnedFinancialAsset o WHERE" +
			" p.ownedFinancialAsset = o " +
			"AND o.investPortfolio = ?1 " +
			"AND p.date = ?2")
	BigDecimal getTotalPriceOfInvestPortfolio(InvestPortfolio investPortfolio, LocalDateTime date);

	@Query(value = "SELECT new ru.akapich.invest_portfolio.model.forms.sql.FormDatePriceGraphSQLQuery(h.date, SUM(h.total), h.ownedFinancialAsset.investPortfolio)" +
			" FROM HistoryAmount h WHERE h.ownedFinancialAsset.investPortfolio = ?1" +
			" GROUP BY h.date, h.ownedFinancialAsset.investPortfolio")
	List<FormDatePriceGraphSQLQuery> getGeneralDatePriceByInvestPortfolio(InvestPortfolio investPortfolio);

	Set<HistoryAmount> findAllByOwnedFinancialAsset_InvestPortfolioAndDate(InvestPortfolio investPortfolio, LocalDateTime date);

	@Query(value = "SELECT new ru.akapich.invest_portfolio.model.forms.sql.FormDatePriceGraphSQLQuery(h.date, h.total, h.ownedFinancialAsset.investPortfolio)" +
			" FROM HistoryAmount h WHERE" +
			" h.ownedFinancialAsset.investPortfolio = ?1" +
			" AND h.ownedFinancialAsset.FinancialAssetInUse.idAllFinancialAsset.ticker = ?2")
	List<FormDatePriceGraphSQLQuery> getAllDatePriceValueByInvestPortfolioAndTicker(InvestPortfolio investPortfolio, String ticker);

	@Query("SELECT h.date FROM HistoryAmount h WHERE h.ownedFinancialAsset.investPortfolio = ?1 GROUP BY h.date")
	LinkedList<LocalDateTime> getUniqueTime(InvestPortfolio investPortfolio);

	LinkedList<HistoryAmount> findAllByOwnedFinancialAsset_InvestPortfolio(InvestPortfolio investPortfolio);

	@Query("SELECT new ru.akapich.invest_portfolio.model.forms.sql.FormPurchaseDate(h.ownedFinancialAsset,  MIN(h.date), h.ownedFinancialAsset.investPortfolio)" +
			" FROM HistoryAmount h WHERE h.ownedFinancialAsset.investPortfolio = ?1 GROUP BY h.ownedFinancialAsset, h.ownedFinancialAsset.investPortfolio")
	List<FormPurchaseDate> getAllPurchaseDateByInvestPortfolio(InvestPortfolio investPortfolio);

//	@Query("SELECT h FROM HistoryAmount h WHERE h.ownedFinancialAsset.investPortfolio = ?1 GROUP BY h.ownedFinancialAsset")
//	List<HistoryAmount> getUniqueTickersByInvestPortfolio(InvestPortfolio investPortfolio);

//DEBAG
//	@Query("SELECT h FROM HistoryAmount h WHERE h.ownedFinancialAsset.investPortfolio = ?1")
//	List<HistoryAmount> getUniqueTickersByInvestPortfolio(InvestPortfolio investPortfolio);


//	@Query("SELECT h FROM HistoryAmount h WHERE h.ownedFinancialAsset.investPortfolio = ?1")
//	List<HistoryAmount> getAllPurchaseDateByInvestPortfolio(InvestPortfolio investPortfolio);
}
