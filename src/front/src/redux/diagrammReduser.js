import { DataAPI } from '../api/api';
import { toggleIsFetching } from './apiReduser';

const getChartData = () => ([
	{
		"name": "name 1",
		"ticker": "ticker 1",
		"percent": 10,
		"value": 500
	},
	{
		"name": "name 2",
		"ticker": "ticker 2",
		"percent": 70,
		"value": 5500
	},
	{
		"name": "name 3",
		"ticker": "ticker 3",
		"percent": 20,
		"value": 3900
	}
])

const SET_TOTAL_DIAGRAMM_DATA = 'SET_TOTAL_DIAGRAMM_DATA';

const initialState = {
	data: null
}

const graphReduser = (state = initialState, action) => {
	switch (action.type) {
		case SET_TOTAL_DIAGRAMM_DATA: {
			return {
				...state,
				data: [...action.data]
			}
		}
		default: {
			return state;
		}
	}
}

export const setTotalDiagrammData = (data) => ({type: SET_TOTAL_DIAGRAMM_DATA, data})

export const getDiagrammData = () => (dispatch) => {
	dispatch(toggleIsFetching(true));
	DataAPI.getDiagrammData()
		.then(res => {
			if (res.resultCode === 0) {
				dispatch(setTotalDiagrammData(res.data));
			}
			dispatch(toggleIsFetching(false));
		})
		.catch(err => {

		})
		.finally(() => {
			dispatch(toggleIsFetching(false));
			dispatch(setTotalDiagrammData(getChartData()));
		})
}

export default graphReduser;