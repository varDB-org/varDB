// namespace
var vardb = {};

//create table
vardb.createTable = function( element, params, id ) {
    var size = 25;

    var header = '<table><tr><td id="' + id + '-pagesize-field"></td>'
    		   + '<td id="' + id + '-tableinfo" style="text-align: right;"></td>'
    		   + '</tr></table>';
    var footer = '<table><tr><td id="' + id + '-pagenumber">Page: '
    	       + '<input type="text" id="' + id + '-page-number" style="width: 90px;">'
    	       + ' of <span id="' + id + '-max-page"></span> '
    	       + '<a href="javascript:jPost.onClickChangePageButton(' + "'" + id + "'" + ')"><img '
    	       + 'src="img/icon_update32.png" width="24" /></a></td>'
	           + '<td id="' + id + '-operation" style="text-align: right;"></td>'
	           + '</tr></table>';
    element.html( header );
	element.append( '<table id="' + id + '" class="table"></table>' );
	element.append( footer );

	params.paginationSize = size;
	params.resizableColumns = true;
	params.tooltips = true;
  	params.pagination = 'remote',
  	params.layout = 'fitColumns',
	params.pageLoaded = vardb.onLoadPage( id );
  	params.ajaxSorting = true;
    params.ajaxFiltering = true;
    params.ajaxResponse = vardb.onTableResponse( id );
    params.paginationElement = $( '#' + id + '-operation' );

    $( '#' + id + '-page-number' ).numeric( { decimal: false, negative: false } );

	$( '#' + id + '-pagesize-field' ).html( 'Page Size: <select id="' + id + '-pagesize"></select>' );
	[ 5, 10, 25, 50, 75, 100 ].forEach(
	    function( page ) {
		    $( '#' + id + '-pagesize' ).append( '<option>' + page + '</option>' )
		}
	);

	$( '#' + id + '-pagesize' ).val( size );
	$( '#' + id + '-pagesize' ).change( vardb.onChangePageSize( id ) );
	$( '#' + id ).tabulator( params );
}

// on table response
vardb.onTableResponse = function( id ) {
    var result = function( url, params, response ) {
    	var page = response.current_page;
    	var size = response.page_size;
    	var total = response.total_count;
    	var count = response.filtered_count;
    	var pages = response.last_page;

    	var first = ( page - 1 ) * size + 1;
    	var last = Math.min( page * size, count );
    	if( first > last ) {
    		first = last;
    	}

    	var message = 'Showing ' + first + ' to ' + last
           	  		+ ' of ' + count + ' entries';
    	if( total > count ) {
    		message = message + ' (filtered from ' + total + ' entries)';
    	}

    	$( '#' + id + '-tableinfo' ).html( message );
    	$( '#' + id + '-page-number' ).val( page );
    	$( '#' + id + '-max-page ').html( pages );

    	return response;
    };

	return result;
}


// on change page size
vardb.onChangePageSize = function( id ) {
	var result = function() {
	    var size = $( '#' + id + '-pagesize' ).val();
			$( '#' + id ).tabulator( 'setPageSize', size );
	};

	return result;
}

// on load page
vardb.onLoadPage = function( id ) {
    var result = function() {
	};

	return result;
}

// on click change page button
vardb.onClickChangePageButton = function( id ) {
    var maxPage = $( '#' + id ).tabulator( 'getPageMax' );
    var page = $( '#' + id + '-page-number' ).val();
    page = Math.max( 1, Math.min( maxPage, parseInt( page ) ) );
    $( '#' + id ).tabulator( 'setPage', page );
}

// on click search button
vardb.onClickSearchButton = function() {
	var url = $( '#table' ).tabulator( 'getAjaxUrl' );
	var keyword = $( '#input-keyword' ).val();
	$( '#table' ).tabulator( 'setData', url, { keyword: keyword } );
}

// family columns
vardb.getFamilyColumns = function() {
	var columns = [
        { title: 'Name', field: 'link', minWidth: 150, formatter: 'html' },
        { title: 'Abbreviation', field: 'abbr', minWidth: 150 },
        { title: 'Pathogen', field: 'pathogen', minWidth: 150, formatter: 'html' },
        { title: 'Ortholog', field: 'ortholog', minWidth: 150, formatter: 'html' },
        { title: 'Description', field: 'description', minWidth: 250 },
        { title: '#Sequence', field: 'numsequences', minWidth: 100, align: 'right' }
    ];
	return columns
}

//create family page
vardb.createFamilyTable = function() {
    var params = {
        columns: vardb.getFamilyColumns(),
        ajaxURL: 'families',
        ajaxConfig: 'GET',
        ajaxParams:{ keyword: $( '#input-keyword' ).val() },
        initialSort: [
            { column: 'link', dir: 'asc' }
        ]
    };

	vardb.createTable( $( '#content' ), params, 'table' );
	$( '#button-search' ).click( vardb.onClickSearchButton );
}

// create ortholog family table
vardb.createOrthologFamilyTable = function( id ) {
	var params = {
		columns: vardb.getFamilyColumns(),
		ajaxURL: 'ortholog_families',
		ajaxConfig: 'GET',
		ajaxParams:{ id: id },
		initialSort: [
			{ column: 'link', dir: 'asc' }
		]
	};

	vardb.createTable( $( '#families' ), params, 'family' );
}

//create ortholog page
vardb.createOrthologTable = function() {
    var params = {
        columns: [
        	{ title: 'Accession', field: 'link', minWidth: 150, formatter: 'html' },
            { title: 'Name', field: 'name', minWidth: 150 },
            { title: 'Description', field: 'description', minWidth: 200 },
            { title: 'KEGG Ortholog', field: 'keggOrtholog', minWidth: 150 },
            { title: 'KEGG Pathway', field: 'keggPathway', minWidth: 150 },
        ],
        ajaxURL: 'orthologs',
        ajaxConfig: 'GET',
        ajaxParams:{ keyword: $( '#input-keyword' ).val() },
        initialSort: [
            { column: 'link', dir: 'asc' }
        ]
    };

	vardb.createTable( $( '#content' ), params, "table" );
	$( '#button-search' ).click( vardb.onClickSearchButton );
}

// taxonomy columns
vardb.getTaxonColumns = function() {
	 var columns = [
     	{ title: 'Accession', field: 'link', minWidth: 100, formatter: 'html' },
         { title: 'Name', field: 'name', minWidth: 150 },
         { title: 'Description', field: 'description', minWidth: 200 },
         { title: 'Level', field: 'level', minWidth: 150 },
         { title: 'Tax ID', field: 'taxid', minWidth: 150 },
         { title: 'Left', field: 'lft', minWidth: 150, align: 'right' },
         { title: 'Right', field: 'rght', minWidth: 150, align: 'right' },
     ];

	 return columns;
}

//create taxonomy page
vardb.createTaxonomyTable = function() {
    var params = {
        columns: vardb.getTaxonColumns(),
        ajaxURL: 'taxons',
        ajaxConfig: 'GET',
        ajaxParams:{ keyword: $( '#input-keyword' ).val() },
        initialSort: [
            { column: 'link', dir: 'asc' }
        ]
    };

	vardb.createTable( $( '#content' ), params, "table" );
	$( '#button-search' ).click( vardb.onClickSearchButton );
}

//create child taxonomy page
vardb.createTaxonChildTable = function( id ) {
    var params = {
        columns: vardb.getTaxonColumns(),
        ajaxURL: 'taxon_children',
        ajaxConfig: 'GET',
        ajaxParams:{ id: id },
        initialSort: [
            { column: 'link', dir: 'asc' }
        ]
    };

	vardb.createTable( $( '#children' ), params, "children" );
}

//create paralog page
vardb.createParalogTable = function() {
    var params = {
        columns: [
        	{ title: 'Accession', field: 'accession', minWidth: 100 },
            { title: 'Name', field: 'name', minWidth: 150 },
            { title: 'Description', field: 'description', minWidth: 200 },
            { title: 'Alignment ID', field: 'alignmentId', minWidth: 150 },
            { title: 'Genome ID', field: 'genomeId', minWidth: 150 },
            { title: 'Family', field: 'family', minWidth: 150 },
            { title: '#Sequence', field: 'numsequences', minWidth: 100, align: 'right' }
        ],
        ajaxURL: 'paralogs',
        ajaxConfig: 'GET',
        ajaxParams:{ keyword: $( '#input-keyword' ).val() },
        initialSort: [
            { column: 'accession', dir: 'asc' }
        ]
    };

	vardb.createTable( $( '#content' ), params, "table" );
	$( '#button-search' ).click( vardb.onClickSearchButton );
}

// disease columns
vardb.getDiseaseColumns = function() {
	var columns = [
    	{ title: 'Accession', field: 'link', minWidth: 200, formatter: 'html' },
        { title: 'Name', field: 'name', minWidth: 150 },
        { title: 'Description', field: 'description', minWidth: 200 },
        { title: 'Diagnosis', field: 'diagnosis', minWidth: 200 },
        { title: 'Drugs', field: 'drugs', minWidth: 200 },
        { title: 'Human', field: 'human', minWidth: 50, align: 'center' },
        { title: 'Distribution', field: 'distribution', minWidth: 200 },
        { title: 'History', field: 'history', minWidth: 200 },
        { title: 'Host', field: 'host', minWidth: 200 },
        { title: 'ICD10', field: 'icd10', minWidth: 200 },
        { title: 'KEGG Disease', field: 'keggDisease', minWidth: 200 },
        { title: 'KEGG Pathway', field: 'keggPathway', minWidth: 200 },
        { title: 'Morbidity', field: 'morbidity', minWidth: 200 },
        { title: 'Mortality', field: 'mortality', minWidth: 200 },
        { title: 'Prevention', field: 'prevention', minWidth: 200 },
        { title: 'Symptoms', field: 'symptoms', minWidth: 200 },
        { title: 'Transmission', field: 'transmission', minWidth: 200 },
        { title: 'Treatment', field: 'treatment', minWidth: 200 },
        { title: 'Vaccines', field: 'vaccines', minWidth: 200 },
        { title: 'Vector', field: 'vector', minWidth: 200 },
        { title: '#Sequence', field: 'numsequences', minWidth: 100, align: 'right' }
    ];

	return columns;
}

//create disease page
vardb.createDiseaseTable = function() {
    var params = {
        columns: vardb.getDiseaseColumns(),
        ajaxURL: 'diseases',
        ajaxConfig: 'GET',
        ajaxParams:{ keyword: $( '#input-keyword' ).val() },
        initialSort: [
            { column: 'link', dir: 'asc' }
        ]
    };

	vardb.createTable( $( '#content' ), params, "table" );
	$( '#button-search' ).click( vardb.onClickSearchButton );
}

//create disease page
vardb.createPathogenDiseaseTable = function( id ) {
    var params = {
        columns: vardb.getDiseaseColumns(),
        ajaxURL: 'pathogen_diseases',
        ajaxConfig: 'GET',
        ajaxParams:{ id: id },
        initialSort: [
            { column: 'link', dir: 'asc' }
        ]
    };

	vardb.createTable( $( '#diseases' ), params, "disease" );
}

//create disease page
vardb.createDrugDiseaseTable = function( id ) {
    var params = {
        columns: vardb.getDiseaseColumns(),
        ajaxURL: 'drug_diseases',
        ajaxConfig: 'GET',
        ajaxParams:{ id: id },
        initialSort: [
            { column: 'link', dir: 'asc' }
        ]
    };

	vardb.createTable( $( '#diseases' ), params, "disease" );
}

// pathogen columns
vardb.getPathogenColumns = function() {
    var columns = [
    	{ title: 'Accession', field: 'link', minWidth: 200, formatter: 'html' },
    	{ title: 'Abbreviation', field: 'abbr', minWidth: 150 },
    	{ title: 'Name', field: 'name', minWidth: 150 },
    	{ title: 'Description', field: 'description', minWidth: 200 },
    	{ title: 'Alias', field: 'alias', minWidth: 200 },
    	{ title: 'Chromosomes', field: 'chromosomes', minWidth: 200 },
    	{ title: 'Genome', field: 'genome', minWidth: 200 },
    	{ title: 'Distribution', field: 'distribution', minWidth: 200 },
    	{ title: 'Hosts', field: 'hosts', minWidth: 200 },
    	{ title: 'GC Content', field: 'gccontent', minWidth: 200 },
    	{ title: 'Dtype', field: 'dtype', minWidth: 200 },
    	{ title: 'KEGG', field: 'kegg', minWidth: 200 },
    	{ title: 'KEGG Organism', field: 'keggOrganism', minWidth: 200 },
    	{ title: 'KEGG Pathway', field: 'keggPathway', minWidth: 200 },
    	{ title: 'Lifecycle', field: 'lifecycle', minWidth: 200 },
    	{ title: 'Taxonomy Group', field: 'taxgroup', minWidth: 200 },
    	{ title: 'Anti Genic Variation', field: 'antigenicvariation', minWidth: 200 },
    	{ title: 'Bacteria Appendages', field: 'bacteriaAppendages', minWidth: 200 },
    	{ title: 'Bacteria Gram', field: 'bacteriaGram', minWidth: 200 },
    	{ title: 'Bacteria Morphology', field: 'bacteriaMorphology', minWidth: 200 },
    	{ title: 'Bacteria Plasmids', field: 'bacteriaPlasmids', minWidth: 200 },
    	{ title: 'Bacteria Size', field: 'bacteriaSize', minWidth: 200 },
    	{ title: 'Virus Baltimore', field: 'virusBaltimore', minWidth: 200 },
    	{ title: 'Virus Nucleicacidtype', field: 'virusNucleicacidtype', minWidth: 200 },
    	{ title: 'Virus Sense', field: 'virusSense', minWidth: 200 },
    	{ title: 'Virus Shape', field: 'virusShape', minWidth: 200 },
    	{ title: 'Virus Size', field: 'virusSize', minWidth: 200 },
    	{ title: 'Virus Strandedness', field: 'virusStrandedness', minWidth: 200 },
    	{ title: '#Base', field: 'numbases', minWidth: 100, align: 'right' },
    	{ title: '#Gene', field: 'numgenes', minWidth: 100, align: 'right' },
    	{ title: '#Protein', field: 'numproteins', minWidth: 100, align: 'right' },
    	{ title: '#Sequence', field: 'numsequences', minWidth: 100, align: 'right' }
    ];
    return columns;
}

//create pathogen page
vardb.createPathogenTable = function() {
    var params = {
        columns: vardb.getPathogenColumns(),
        ajaxURL: 'pathogens',
        ajaxConfig: 'GET',
        ajaxParams:{ keyword: $( '#input-keyword' ).val() },
        initialSort: [
            { column: 'link', dir: 'asc' }
        ]
    };

	vardb.createTable( $( '#content' ), params, "table" );
	$( '#button-search' ).click( vardb.onClickSearchButton );
}

//create pathogen page
vardb.createDrugPathogenTable = function( id ) {
    var params = {
        columns: vardb.getPathogenColumns(),
        ajaxURL: 'drug_pathogens',
        ajaxConfig: 'GET',
        ajaxParams:{ id: id },
        initialSort: [
            { column: 'link', dir: 'asc' }
        ]
    };

	vardb.createTable( $( '#pathogens' ), params, "pathogen" );
}

//create pathogen page
vardb.createDiseasePathogenTable = function( id ) {
    var params = {
        columns: vardb.getPathogenColumns(),
        ajaxURL: 'disease_pathogens',
        ajaxConfig: 'GET',
        ajaxParams:{ id: id },
        initialSort: [
            { column: 'link', dir: 'asc' }
        ]
    };

	vardb.createTable( $( '#pathogens' ), params, "pathogen" );
}

//create taxonomy pathogen page
vardb.createTaxonPathogenTable = function( id ) {
    var params = {
        columns: vardb.getPathogenColumns(),
        ajaxURL: 'taxon_pathogens',
        ajaxConfig: 'GET',
        ajaxParams:{ id: id },
        initialSort: [
            { column: 'link', dir: 'asc' }
        ]
    };

	vardb.createTable( $( '#pathogens' ), params, "pathogen" );
}

// drug columns
vardb.getDrugColumns = function() {
	var columns = [
    	{ title: 'Accession', field: 'link', minWidth: 150, formatter: 'html' },
        { title: 'Name', field: 'name', minWidth: 150 },
        { title: 'Description', field: 'description', minWidth: 200 },
        { title: 'Activity', field: 'activity', minWidth: 200 },
        { title: 'Formula', field: 'formula', minWidth: 200 },
        { title: 'Mass', field: 'mass', minWidth: 200 },
        { title: 'Names', field: 'names', minWidth: 200 },
        { title: 'Notes', field: 'notes', minWidth: 200 },
        { title: 'Target', field: 'target', minWidth: 200 },
        { title: '#Sequence', field: 'numsequences', minWidth: 100, align: 'right' }
    ];
	return columns;
}

//create drug page
vardb.createDrugTable = function() {
    var params = {
        columns: vardb.getDrugColumns(),
        ajaxURL: 'drugs',
        ajaxConfig: 'GET',
        ajaxParams:{ keyword: $( '#input-keyword' ).val() },
        initialSort: [
            { column: 'link', dir: 'asc' }
        ]
    };

	vardb.createTable( $( '#content' ), params, "table" );
	$( '#button-search' ).click( vardb.onClickSearchButton );
}

//create drug table
vardb.createPathogenDrugTable = function( id ) {
    var params = {
        columns: vardb.getDrugColumns(),
        ajaxURL: 'pathogen_drugs',
        ajaxConfig: 'GET',
        ajaxParams:{ id: id },
        initialSort: [
            { column: 'link', dir: 'asc' }
        ]
    };

	vardb.createTable( $( '#drugs' ), params, "drug" );
}

//create drug table
vardb.createDiseaseDrugTable = function( id ) {
    var params = {
        columns: vardb.getDrugColumns(),
        ajaxURL: 'disease_drugs',
        ajaxConfig: 'GET',
        ajaxParams:{ id: id },
        initialSort: [
            { column: 'link', dir: 'asc' }
        ]
    };

	vardb.createTable( $( '#drugs' ), params, "drug" );
}

// reference columns
vardb.getReferenceColumns = function() {
	var columns = [
    	{ title: 'Accession', field: 'link', minWidth: 100, formatter: 'html' },
        { title: 'Name', field: 'name', minWidth: 150 },
        { title: 'Type', field: 'type', minWidth: 150 },
        { title: 'Description', field: 'description', minWidth: 200 },
        { title: 'Title', field: 'title', minWidth: 200 },
        { title: 'Authors', field: 'authors', minWidth: 200 },
        { title: 'Journal', field: 'journal', minWidth: 200 },
        { title: 'Year', field: 'year', minWidth: 200 },
        { title: 'Pages', field: 'pages', minWidth: 200 },
        { title: 'PMID', field: 'pmid', minWidth: 200 },
        { title: 'Publisher', field: 'publisher', minWidth: 200 },
        { title: 'Abstract', field: 'abstract', minWidth: 200 },
        { title: 'City', field: 'city', minWidth: 200 },
        { title: 'Visible', field: 'visible', minWidth: 50, align: 'center' },
        { title: 'Volume', field: 'volume', minWidth: 200 },
        { title: '#Sequence', field: 'numsequences', minWidth: 100, align: 'right' }
    ];

	return columns;
}

//create reference page
vardb.createReferenceTable = function() {
    var params = {
        columns: vardb.getReferenceColumns(),
        ajaxURL: 'references',
        ajaxConfig: 'GET',
        ajaxParams:{ keyword: $( '#input-keyword' ).val() },
        initialSort: [
            { column: 'link', dir: 'asc' }
        ]
    };

	vardb.createTable( $( '#content' ), params, "table" );
	$( '#button-search' ).click( vardb.onClickSearchButton );
}

//create family reference page
vardb.createFamilyReferenceTable = function( id ) {
    var params = {
    	columns: vardb.getReferenceColumns(),
        ajaxURL: 'family_refs',
        ajaxConfig: 'GET',
        ajaxParams:{ id: id },
        initialSort: [
            { column: 'link', dir: 'asc' }
        ]
    };

   	vardb.createTable( $( '#refs' ), params, "reference" );
}

//create ortholog reference page
vardb.createOrthologReferenceTable = function( id ) {
	var params = {
		columns: vardb.getReferenceColumns(),
		ajaxURL: 'ortholog_refs',
		ajaxConfig: 'GET',
		ajaxParams:{ id: id },
		initialSort: [
			{ column: 'link', dir: 'asc' }
		]
	};

	vardb.createTable( $( '#refs' ), params, "reference" );
}

//create pathogen reference page
vardb.createPathogenReferenceTable = function( id ) {
	var params = {
		columns: vardb.getReferenceColumns(),
		ajaxURL: 'pathogen_refs',
		ajaxConfig: 'GET',
		ajaxParams:{ id: id },
		initialSort: [
			{ column: 'link', dir: 'asc' }
		]
	};

	vardb.createTable( $( '#refs' ), params, "reference" );
}
