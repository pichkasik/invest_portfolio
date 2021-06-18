import { AuthAPI } from '../api/api';

export const SET_AUTH_USER_DATA = 'SET_AUTH_USER_DATA';


const initialState = {
	name: null,
	email: null,
	userID: null,
	isAuth: localStorage.getItem('isAuth') || false,
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
			}
		})
		.catch(err => {
			return err.message
		})
}

export const logout = () => (dispatch) => {
	return AuthAPI.logout()
		.then(res => {
			if (res.resultCode === 0) {
				dispatch(setAuthUserData(null, null, null, false));
			}
		})
		.catch(err => {
			return err.message
		})
}

export const signUp = ({name, email, password, rePassword}) => (dispatch) => {
	return AuthAPI.signUp(name, email, password, rePassword)
		.then(res => {
			if (res.resultCode === 0) {
				dispatch(setAuthUserData(res.userID, res.email, res.name, true)); //TODO getAuthUserData для получения информации залогиненого пользователя
			} else {
				return res.error;
			}
		})
		.catch(err => {
			return err.message
		})
}

export default authReduser;