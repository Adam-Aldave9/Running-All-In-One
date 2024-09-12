import { useEffect, useState } from "react";
import axios from "axios";

function SignUp(): JSX.Element {
	const [newUser, setNewUser] = useState({
        username: "",
        password: "",
		date_created: ""
    })

	useEffect(() => {
		const date = new Date();
		let day = String(date.getDate()).padStart(2, '0');
		let month = String(date.getMonth() + 1).padStart(2, '0');
		let year = date.getFullYear();
		let dateCreated = month + '/' + day + '/' + year;
		setNewUser({
			...newUser,
			date_created: dateCreated
		})
	}, [])

	const [exists, setExists] = useState("")

	function onChangeUsername(e: React.ChangeEvent<HTMLInputElement>): void{
        setNewUser({
            ...newUser,
            username: e.target.value
        })
    }

    function onChangePassword(e: React.ChangeEvent<HTMLInputElement>): void{
        setNewUser({
            ...newUser,
            password: e.target.value
        })
    }

	async function onSignUp(e: React.MouseEvent<HTMLButtonElement>): Promise<void>{
		try{
			let responseCred = await axios.post("http://localhost:80/credentials/add", newUser)
			if(responseCred.status == 200){
				console.log("Credentials added")
				window.location.assign("http://localhost:3000/")
			}
			else if(responseCred.status == 409){
				console.log("Username already exists")
				setExists("Username already exists. Try Again")
			}
			else{
				console.log("Credentials not added")
			}
		}catch(e){
			console.log(e)
		}
	}
	
    return (
        <section className="min-h-screen from-purple-200 via-purple-300 to-purple-500 bg-gradient-to-br">
			{/* Container */}
			<div className="mx-auto w-full max-w-3xl px-5 py-16 md:px-10 md:py-20">
				{/* Component */}
				<div className="mx-auto max-w-xl bg-gray-100 px-8 py-12 text-center">
					{/* Title */}
					<h2 className="text-orange-700 text-3xl mx-auto text-center font-bold max-w-sm md:text-5xl">Running Community</h2>
					<p className="mx-auto my-5 max-w-md text-sm text-black-400 sm:text-base lg:mb-8">
						Sign up today to be part of the community!
					</p>
					<div className="mx-auto w-full max-w-sm">
					
						{/* Form */}
						<div className="mx-auto mb-4 max-w-sm pb-4">
							<div className="relative">
								<img
									alt=""
									src="https://assets.website-files.com/6458c625291a94a195e6cf3a/6458c625291a9455fae6cf89_EnvelopeSimple.svg"
									className="absolute left-5 top-3 inline-block"
								/>
								<input
									className="mb-4 block h-9 w-full rounded-md border border-solid border-black px-3 py-6 pl-14 text-sm text-black placeholder:text-black"
									placeholder="Username"
									required
									value={newUser.username}
									onChange={onChangeUsername}
								/>
							</div>
							<div className="relative mb-4">
								<img
									alt=""
									src="https://assets.website-files.com/6458c625291a94a195e6cf3a/6458c625291a946794e6cf8a_Lock-2.svg"
									className="absolute left-5 top-3 inline-block"
								/>
								<input
									className="mb-4 block h-9 w-full rounded-md border border-solid border-black px-3 py-6 pl-14 text-sm text-black placeholder:text-black"
									placeholder="Password"
									required
									value={newUser.password}
									onChange={onChangePassword}
								/>
							</div>
							<button onClick={onSignUp} className="mt-4 inline-block w-full cursor-pointer items-center rounded-md bg-black px-6 py-3 text-center font-semibold text-white">
								Sign up
							</button>
							
						</div>
						<p className="text-sm text-gray-500">
							<a href="/" className="font-bold underline ">
								<span>Back</span> 
							</a>
							<div className="mt-8">{exists}</div>
						</p>
					</div>
				</div>
			</div>
		</section>
    );
}

export default SignUp;