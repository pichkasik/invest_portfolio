package ru.akapich.invest_portfolio.model.portfolio.asset_data.info_assets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.FinancialAssetInUse;

import javax.persistence.*;

/**
 * JavaBean object that represents name of currency(USD, EUR etc)
 * of {@link FinancialAssetInUse}
 *
 * @author Aleksandr Marakulin
 **/

@Entity
@Table(name="t_currency")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Currency {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String name;
}
