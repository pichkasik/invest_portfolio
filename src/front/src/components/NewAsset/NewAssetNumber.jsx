import Input from '../Basic/Input/Input';

const NewAssetNumber = ({input, meta, ...props}) => {
	return (
		<Input
			{...input}
			type='text'
			placeholder='Количество'
		/>
	)
}

export default NewAssetNumber;