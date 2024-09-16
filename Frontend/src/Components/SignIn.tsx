import { useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";

function SignIn(): JSX.Element {
	const [user, setUser] = useState({
		username: "",
		password: "",
	});
	const [success, setSuccess] = useState("");

	function onChangeUsername(e: React.ChangeEvent<HTMLInputElement>): void {
		setUser({
			...user,
			username: e.target.value
		});
	}

	function onChangePassword(e: React.ChangeEvent<HTMLInputElement>): void {
		setUser({
			...user,
			password: e.target.value
		});
	}

	async function onSignIn(): Promise<void> {
		const params = {
			username: user.username,
			password: user.password
		};
		let response = await axios.get(`http://localhost:80/credentials/verify/${params.username}/${params.password}`);
		console.log(response.data.exists);
		if(response.data.exists){
			window.location.assign("http://localhost:3000/viewsessions/"+ user.username)
		}
		else{
			setSuccess("Incorrect Username or Password")
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
						Meet people who like running and schedule your next run together.
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
									value={user.username}
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
									type="password"
									className="mb-4 block h-9 w-full rounded-md border border-solid border-black px-3 py-6 pl-14 text-sm text-black placeholder:text-black"
									placeholder="Password"
									required
									value={user.password}
									onChange={onChangePassword}
								/>
							</div>
							<button onClick={onSignIn} className="mt-4 inline-block w-full cursor-pointer items-center rounded-md bg-black px-6 py-3 text-center font-semibold text-white">
								Sign in
							</button>
						
						</div>
						<p className="text-sm text-gray-500">
							Already have an account?
							<a href="/signup" className="font-bold">
								<span> </span> Sign up Now
							</a>
							<div className="mt-8">{success}</div>
						</p>
					</div>
				</div>
			</div>
		</section>
    );
}

export default SignIn;