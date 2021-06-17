import styled from 'styled-components';
import { Field } from 'react-final-form';
import Search from '../Search/Search';
import NewAssetNumber from './NewAssetAmount';
import AddNewAsset from './AddNewAsset';
import { requiredField } from '../../utils/validators'

const NewAssetContainer = styled.div`
	display: flex;
	align-items: flex-end;
	justify-content: space-between;
	margin-bottom: 25px;
	position: relative;
`

const NewAsset = (props) => {
	return (
		<NewAssetContainer>
			<Field 
				name={'search-' + props.id}
				mutators={props.form.mutators}
				validate={requiredField}
			>
				{props => <Search {...props} labelText='Выберите актив' />}
			</Field>

			<Field 
				name={'amount-' + props.id}
				validate={requiredField}
			>
				{props => <NewAssetNumber labelText='Колличество' {...props} />}
			</Field>
			<AddNewAsset />
		</NewAssetContainer>
	)
}

export default NewAsset;