package ru.akapich.invest_portfolio.model.forms.sql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.repository.portfolio.history_data.HistoryAmountRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Form to get data from db {@link HistoryAmountRepository} for graph values
 *
 * @author Aleksandr Marakulin
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormDatePriceGraphSQLQuery {

	private LocalDateTime date;
	private BigDecimal total;
	private InvestPortfolio investPortfolio;
}
