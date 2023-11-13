import Link from "next/link";
import { useEffect, useState } from "react";
import { montserrat, dm_sans } from "@/utils/fonts";
import Icon from "@/components/Icon";
import InputWithLabel from "@/components/InputWithLabel";
import Button from "@/components/Button";
import { useToast } from "@/components/ui/use-toast";
import axios from "axios";
import { useRouter } from "next/navigation";
import { useUserInfoStore } from "@/store/userInfoStore";

const inputClassname =
  "block w-full rounded-md border-0 py-1.5 px-3 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 border-none focus:ring-1 focus:outline-custom-orange sm:text-sm sm:leading-6";

export default function LoginPage() {
  const fetchUserInfo = useUserInfoStore((state)=> state.fetchUserInfo)
  const [passwordView, setPasswordView] = useState(true);
  const { toast } = useToast();
  const router = useRouter();

  const handleFormSubmit = (e) => {
    e.preventDefault();
    const formdata = new FormData(e.target);
    const jsonData = Array.from(formdata.entries()).reduce(
      (acc, [key, value]) => {
        acc[key] = value;
        return acc;
      },
      {}
    );

    axios
      .post(`${process.env.NEXT_PUBLIC_BASE_API_URL}/auth/login`, jsonData)
      .then((res) => {
        console.log(res);
        if (res.status === 200) {
          toast({
            variant: "primary",
            description: "Successfully logged in, now redirecting",
          });

          localStorage.setItem("edu_exchange_access_token", res.data.token);
          fetchUserInfo()
          router.push("/posts");
        }
      })
      .catch((err) => {
        if (err.message.includes("403")) {
          toast({
            variant: "destructive",
            description: "Username or password incorrect",
          });
        } else {
          toast({
            variant: "destructive",
            description: err.message,
          });
        }
      });
  };

  return (
    <>
      <div className="flex items-center flex-1 flex-col justify-center px-6 py-12 lg:px-8 w-screen h-screen" style={{backgroundImage: `url("bgimg.png")`}}>
        <div className="">
          <div className=" sm:mx-auto sm:w-full sm:max-w-sm shadow-lg px-10  py-8 rounded-xl bg-white">
            <div className="sm:mx-auto sm:w-full sm:max-w-sm ">
              <h2
                className={`text-center text-3xl font-bold leading-9 tracking-tight text-gray-900 ${dm_sans.className}`}
              >
                Login
              </h2>
              <p
                className={`px-8 mt-3 mb-5 text-sm text-center font-[500] ${montserrat.className}`}
              >
                Hey, Enter your credentials sign in to your account
              </p>
            </div>
            <form className="space-y-6" onSubmit={handleFormSubmit}>
              <InputWithLabel
                type="email"
                label="Your email"
                className={inputClassname}
                autoComplete="email"
                name="email"
                placeholder="email@company.com"
                required
              />

              <div>
                <label
                  htmlFor="password"
                  className="block mb-2 text-sm font-medium text-gray-900 "
                >
                  Your password
                </label>
                <div className="mt-2 relative">
                  <div className="absolute top-2/4 right-1 grid h-5 w-5 -translate-y-2/4 place-items-center text-blue-gray-500 cursor-pointer">
                    <Icon
                      name={passwordView ? "eye" : "eye-off"}
                      size={16}
                      onClick={() => {
                        setPasswordView((prev) => !prev);
                      }}
                    />
                  </div>
                  <input
                    id="password"
                    name="password"
                    type={passwordView ? "password" : "text"}
                    autoComplete="current-password"
                    required
                    className="block w-full rounded-md px-3  border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-1 focus:outline-custom-orange sm:text-sm sm:leading-6"
                  />
                </div>
                <Link href="#" className=" mt-3 mb-5 text-[11px] ">
                  Forgot your password?
                </Link>
              </div>

              <Button
                text="Sign in"
                className="flex w-full justify-center fursor-pointer py-2 px-6 rounded-md bg-custom-orange text-sm font-black text-white"
                type="submit"
              />
            </form>

            <p className="mt-5 text-center text-sm text-gray-500">
              Do not have account?{" "}
              <Link
                href="/register"
                className="font-semibold text-black-500 hover:text-slate-900"
              >
                Register
              </Link>
            </p>
          </div>
        </div>
      </div>
    </>
  );
}
