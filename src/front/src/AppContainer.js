import { useEffect } from 'react';
import App from './App';
import { connect } from 'react-redux';
import { getSettedCategory, getCategories } from './redux/categoryReduser';
import { getTotalAssets } from './redux/assetsReduser';

const AppContainer = (props) => {
	useEffect(() => {
		if (props.isAuth) {
			props.getSettedCategory();
			props.getCategories();
			props.getTotalAssets();
		}
	}, [props.isAuth]);

	return <App />
}

const mapStateToProps = (state) => ({
	isAuth: state.auth.isAuth
})

export default connect(mapStateToProps, { getSettedCategory, getCategories, getTotalAssets })(AppContainer);