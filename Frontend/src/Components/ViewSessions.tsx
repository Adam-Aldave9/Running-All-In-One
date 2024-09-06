import Navbar from "./Navbar";

function ViewSessions() {
    /**
        Use date library to get current date. Based on currernt date display sessions that are upcoming for the month
        in chart that shows sun - sat. Load upcoming months sessions as well as preview option
        
        Need to add join query to backend to combine relevant session data
        */
    function DisplayMonth(props: any) {
        return (
            <div>
                <h1>{props.month}</h1>
                <div>
                    <DisplaySessions></DisplaySessions>
                </div>
            </div>
        )
    }

    function DisplaySessions(props: any) {
        // display sessions based on month selected in ChooseMonth
        return (
            <div>

            </div>
        )
    }

    function ChooseMonth() {    
        return (
            <div>

            </div>
        )
    }


    return (
        <section className = "min-h-screen from-purple-200 via-purple-300 to-purple-500 bg-gradient-to-br">
            <div>
                <Navbar></Navbar>
                <ChooseMonth></ChooseMonth>
                <DisplayMonth></DisplayMonth>
            </div>
        </section>
    )
}

export default ViewSessions;