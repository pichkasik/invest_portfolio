import { AuthAPI } from '../api/api';

export const SET_USER_DATA = 'SET_USER_DATA';


const initialState = {
	login: null,
	email: null,
	userID: null,
	isAuth: localStorage.getItem("isAuth") || false,
}

const authReduser = (state = initialState, action) => {
	switch (action.type) {
		case SET_USER_DATA: {
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

export const setUserData = (userID, email, login, isAuth) => ({ type: SET_USER_DATA, payload: {userID, email, login}, isAuth});

export const login = (login, password, rememberMe) => (dispatch) => {
	AuthAPI.login(login, password, rememberMe)
		.then(res => {
			if (res.resultCode === 0) {
				dispatch(); //TODO getAuthUserData для получения информации залогиненого пользователя
			} else {

			}
		})
}

export default authReduser;