import styled from 'styled-components';

const Button = styled.button`
	padding: 21px 118px;
	width: 100%;
	background-color: #89D7AB;
	border: none;
	border-radius: 6px;
	color: #fff;
	transition: background-color 0.2s ease;

	&:hover {
		background-color: #74B791;
	}
	&:disabled {
		background-color: #428860;
	}
`;

export const HeaderButton = styled(Button)`
	font-weight: bold;
	width: auto;
	padding: 13px 13px 13px 13px;
	margin-bottom: 10px;
	position: relative;
	display: flex;
	align-items: center;
`

export const AddAsset = styled(HeaderButton)`
	margin-right: 20px;
	padding-left: 45px;
	&:before {
		content: '+';
		position: absolute;
		left: 13px;
		color: #fff;
		font-weight: bold;
		font-size: 2em;
		line-height: 1;
		// margin-right: 25px;
	}
`


export default Button;