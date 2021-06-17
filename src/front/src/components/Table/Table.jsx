import TableBody from './TableBody'
import TableHeader from './TableHeader'
import styled from "styled-components";

const StyledTable = styled.div`
	min-width: 100%;
`

const Table = ({ data }) => {
	return (
		<StyledTable>
			<TableHeader data={data.header} />
			<TableBody data={data.body} />
		</StyledTable>
	);
}

export default Table;