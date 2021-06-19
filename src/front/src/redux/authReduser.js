import { AuthAPI } from '../api/api';

export const SET_AUTH_USER_DATA = 'SET_AUTH_USER_DATA';


const initialState = {
	name: null,
	email: null,
	userID: null,
	isAuth: localStorage.getItem('isAuth') || true,
}

const authReduser = (state = initialState, action) => {
	switch (action.type) {
		case SET_AUTH_USER_DATA: {
			return {
				...state,
				...action.payload,
				isAuth: action.isAuth
			}
		}
		default: {
			return state;
		}
	}
}

export const setAuthUserData = (userID, email, name, isAuth) => ({ type: SET_AUTH_USER_DATA, payload: {userID, email, name}, isAuth});

export const login = (params) => (dispatch) => {
	return AuthAPI.login(params)
		.then(res => {
			if (res === 'ok') {
				dispatch(setAuthUserData(res.userID, res.email, 'name', true)); //TODO getAuthUserData для получения информации залогиненого пользователя
			} else {
				throw new Error('Неверный e-mail или пароль');
			}
		})
		.catch(err => {
			throw new Error(err.message);
		})
}

export const logout = () => (dispatch) => {
	AuthAPI.logout()
		.then(() => {
			dispatch(setAuthUserData(null, null, null, false));
		})
}

export const signUp = ({name, email, password, rePassword}) => (dispatch) => {
	return AuthAPI.signUp(name, email, password, rePassword)
		.then(res => {
			if (res.resultCode === 0) {
				dispatch(setAuthUserData(res.userID, res.email, res.name, true)); //TODO getAuthUserData для получения информации залогиненого пользователя
			} else {
				throw new Error(res.error);
			}
		})
		.catch(err => {
			throw new Error(err.message);
		})
}
// gcmsg "add throwing Error in request fail"
export default authReduser;