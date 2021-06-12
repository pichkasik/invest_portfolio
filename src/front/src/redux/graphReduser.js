const SET_DATA = 'SET_DATA';
const RESET_DATA = 'RESET_DATA';
const SET_DATA_INDEX = 'SET_DATA_INDEX';
const SET_SLIDER_DATA = 'SET_SLIDER_DATA';

export const widthPercent = 30;

const initialState = {
	tooltip: {
		top: null,
		left: null,
		title: '',
		data: []
	},
	showTooltip: false,
	mouseX: null,
	dataIndex: {
		left: null,
		right: null
	},
	sliderGraphData: {
		windowWidth: 0,
		windowLeft: 0,
		windowRight: 0,
		rightWidth: 0,
		leftWidth: 0,
	}
}

const graphReduser = (state = initialState, action) => {
	switch (action.type) {
		case SET_DATA: {
			return {
				...state,
				tooltip: {
					...action.tooltip
				},
				showTooltip: true,
				mouseX: action.x
			}
		}
		case RESET_DATA: {
			return {
				...state,
				tooltip: {
					top: null,
					left: null,
					title: '',
					data: []
				},
				showTooltip: false,
				mouseX: null
			}
		}
		case SET_DATA_INDEX: {
			return {
				...state,
				dataIndex: {
					...action.index
				}
			}
		}
		case SET_SLIDER_DATA: {
			return {
				...state,
				sliderGraphData: {
					...action.data
				}
			}
		}
		default: {
			return state;
		}
	}
}

export const setData = ({top, left, title, data, x}) => ({
	type: SET_DATA,
	tooltip: {
		top,
		left,
		title,
		data
	},
	x: x
})

export const resetData = () => ({
	type: RESET_DATA,
})

export const setDataIndex = (left, right) => ({type: SET_DATA_INDEX, index: {left, right}})

export const setSliderGraphData = ({windowWidth, windowLeft, windowRight, rightWidth, leftWidth}) => {
	return {
		type: SET_SLIDER_DATA,
		data: {windowWidth, windowLeft, windowRight, rightWidth, leftWidth}
	}
}

export default graphReduser;