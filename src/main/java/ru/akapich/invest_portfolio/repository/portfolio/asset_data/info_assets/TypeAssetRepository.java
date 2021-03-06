package ru.akapich.invest_portfolio.repository.portfolio.asset_data.info_assets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.info_assets.TypeAsset;

/**
 * JavaBean object that interaction with Database for class {@link TypeAsset}.
 *
 * @author Aleksandr Marakulin
 **/

@Repository
public interface TypeAssetRepository extends JpaRepository<TypeAsset, Integer> {

	TypeAsset findTypeAssetByName(String name);
}
