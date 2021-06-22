import { OptionsDropdownItem, OptionsDropdownList } from './OptionsDropdownMenuStyles';
import { connect } from 'react-redux'; 
import { deleteAsset } from '../../../../redux/assetsTableReduser'

const OptionsDropdownMenu = (props) => {

	return (
		<OptionsDropdownList isOpen={props.isOpen}>
			<OptionsDropdownItem
				onClick={() => {
					props.toggleIsOpen(false);
					props.deleteAsset(props.ticker)
				}}
			>
				Удалить
			</OptionsDropdownItem>
			<OptionsDropdownItem
				onClick={() => {
					props.toggleIsOpen(false);
					props.setEditMode({ticker: props.ticker, type: 'buy'})
				}}
			>
				Купил
			</OptionsDropdownItem>
			<OptionsDropdownItem
				onClick={() => {
					props.toggleIsOpen(false);
					props.setEditMode({ticker: props.ticker, type: 'sell'})
				}}
			>
				Продал
			</OptionsDropdownItem>
		</OptionsDropdownList>
	)
}

export default connect(null, {deleteAsset})(OptionsDropdownMenu);