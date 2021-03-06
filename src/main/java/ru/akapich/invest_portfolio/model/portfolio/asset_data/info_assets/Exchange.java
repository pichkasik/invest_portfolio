package ru.akapich.invest_portfolio.model.portfolio.asset_data.info_assets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.AllFinancialAsset;
import javax.persistence.*;

/**
 * JavaBean object that represents the marked Exchange
 * of {@link AllFinancialAsset}
 *
 * @author Aleksandr Marakulin
 **/

@Entity
@Table(name="t_exchange")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Exchange {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String name;
}
