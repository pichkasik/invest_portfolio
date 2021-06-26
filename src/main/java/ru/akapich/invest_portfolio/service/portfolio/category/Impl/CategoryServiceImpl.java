package ru.akapich.invest_portfolio.service.portfolio.category.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akapich.invest_portfolio.model.forms.assets.BaseResponseForm;
import ru.akapich.invest_portfolio.model.forms.category.CategoryCreateForm;
import ru.akapich.invest_portfolio.model.portfolio.Category;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;
import ru.akapich.invest_portfolio.repository.portfolio.CategoryRepository;
import ru.akapich.invest_portfolio.repository.portfolio.InvestPortfolioRepository;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.OwnedFinancialAssetRepository;
import ru.akapich.invest_portfolio.service.portfolio.category.CategoryService;
import ru.akapich.invest_portfolio.service.user.UserService;

import java.util.List;
import java.util.Set;

/**
 * @author Aleksandr Marakulin
 **/

@Log4j2
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private OwnedFinancialAssetRepository ownedFinancialAssetRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private Environment env;

	@Override
	@Transactional
	public BaseResponseForm addNewCategory(CategoryCreateForm categoryCreateForm) {
		String errorMessage = "";
		InvestPortfolio investPortfolio = userService.getUserInCurrentSession().getInvestPortfolio();
		Category firstExistCategory = categoryRepository.getCategoryByNameAndInvestPortfolio(investPortfolio, categoryCreateForm.getName());
		if (firstExistCategory != null){
			errorMessage = env.getProperty("valid.exist.category");
		}
		else{
			List<OwnedFinancialAsset> ownedFinancialAssets = ownedFinancialAssetRepository
					.getOwnedFinancialAssetsByListTickersAndInvestPortfolio(investPortfolio, categoryCreateForm.getValues());//Could be error
			System.out.println(String.format("owned financial asset EXPECTED: %s", categoryCreateForm.getValues()));
			System.out.println(String.format("owned financial asset GET: %s", ownedFinancialAssets));
//			List<OwnedFinancialAsset> ownedFinancialAssets = ownedFinancialAssetRepository
//					.findOwnedFinancialAssetsByFinancialAssetInUse_IdAllFinancialAssetTicker(categoryCreateForm.getTickers());
			System.out.println("Before creating category");
			Category category = Category.builder()
					.name(categoryCreateForm.getName())
					.idOwnedFinancialAssets(ownedFinancialAssets)
					.build();
			System.out.println(String.format("Create category: %s", category));

			categoryRepository.save(category);
		}
		System.out.println("Before return");
		return BaseResponseForm.builder()
				.error(errorMessage)
				.resultCode("".equals(errorMessage) ? 0 : 1)
				.build();
	}

	@Override
	public void setCategory(String nameCategory) {
		Category category;
		InvestPortfolio investPortfolio = userService.getUserInCurrentSession().getInvestPortfolio();

		if ("total".equals(nameCategory)){
			category = null;
		}
		else{
			category = categoryRepository.getCategoryByNameAndInvestPortfolio(investPortfolio, nameCategory);
		}
		investPortfolio.setCategory(category);
	}

	@Override
	public void deleteCategory(String nameCategory) {

	}
}
