package ru.akapich.invest_portfolio.model.portfolio.asset_data.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.FinancialAssetInUse;

import javax.persistence.*;

/**
 * JavaBean object that represents info a company of
 * {@link FinancialAssetInUse}
 *
 * @author Aleksandr Marakulin
 **/

@Entity
@Table(name="t_company")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Company {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String name;

	@Column
	private String ticker;

//	//foreign key
//	@OneToOne//(mappedBy = "t_company")
//	private FinancialAsset financialAsset;
}
