import Navbar from "./Navbar";

function SearchResults() {
    {/* show results from search */}

    function Document() {
        {/* map results from search */}
        return (
            <li className="flex flex-row mb-2 border-gray-400">
                <div className="shadow border select-none cursor-pointer bg-white dark:bg-gray-800 rounded-md flex flex-1 items-center p-4">
                    
                    <div className="flex-1 pl-1 md:mr-16">
                        <div className="font-medium dark:text-white">
                            Jean Marc
                        </div>
                        <div className="text-sm text-gray-600 dark:text-gray-200">
                            Developer
                        </div>
                    </div>
                </div>
        </li>
        )
    }

    function MapResults() {
        {/* map results from search */}
        return (
            <div>
                <Document />
                <Document />
                <Document />
                <Document />
                <Document />
            </div>
        )
    }

    function ResultsTable() {
        {/* display results in table */}
        return (
            <div className="container flex flex-col items-center justify-center w-full mx-auto">
                <ul className="flex flex-col mt-8">
                    <MapResults />
                </ul>
            </div>

        )
    }

    return (
        <section className = "min-h-screen from-purple-200 via-purple-300 to-purple-500 bg-gradient-to-br">
            <Navbar />
            <div className="mt-8">
                <h1 className="text-2xl font-light text-center">Search Results</h1>
                {/* Table of results and links to partner add pages */}
                <ResultsTable />
                {/* Groups with buttons to join */}
            </div>
        </section>    
    );
}

export default SearchResults;