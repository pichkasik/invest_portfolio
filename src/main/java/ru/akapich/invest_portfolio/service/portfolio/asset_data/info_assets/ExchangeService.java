package ru.akapich.invest_portfolio.service.portfolio.asset_data.info_assets;

import ru.akapich.invest_portfolio.model.portfolio.asset_data.info_assets.Exchange;

/**
 * Service class of {@link Exchange}
 *
 * @author Aleksandr Marakulin
 **/

public interface ExchangeService {

	Exchange findOrAddExchangeByName(String name);
}
