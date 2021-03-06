import PageRenderer from './__fixtures__/PageRenderer.es';
import '../Checkbox/index.es';
import '../Date/index.es';
import '../Grid/index.es';
import '../Radio/index.es';
import '../Select/index.es';
import '../Text/index.es';
import '../Options/index.es';

let component;

describe(
	'Layout Render',
	() => {
		it(
			'should render fields',
			() => {
				component = new PageRenderer(
					{
						items: [
							{type: 'checkbox'},
							{type: 'date'},
							{type: 'grid'},
							{type: 'select'},
							{type: 'text'},
							{type: 'options'}
						]
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);