import Link from "next/link";
import { useState } from "react";
import { montserrat, dm_sans } from "@/utils/fonts";
import InputWithLabel from "@/components/InputWithLabel";
import Button from "@/components/Button";
import axios from "axios";
import { useToast } from "@/components/ui/use-toast";
import Icon from "@/components/Icon";
import { Check, ChevronsUpDown } from "lucide-react";
import { cn } from "@/lib/utils";
import { Button as Button2 } from "@/components/ui/button";
import {
  Command,
  CommandEmpty,
  CommandGroup,
  CommandInput,
  CommandItem,
} from "@/components/ui/command";
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from "@/components/ui/popover";

const inputClassname =
  "block w-full rounded-md border-0 py-1.5 px-3 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 border-none focus:ring-1 focus:outline-custom-orange sm:text-sm sm:leading-6";

const roles = [
  {
    value: "STUDENT",
    label: "STUDENT",
  },
  {
    value: "TEACHER",
    label: "TEACHER",
  },
];

export default function RegisterPage() {
  const [passwordView, setPasswordView] = useState(true);
  const [open, setOpen] = useState(false);
  const [value, setValue] = useState("");
  const {toast} = useToast()

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
      .post(`${process.env.NEXT_PUBLIC_BASE_API_URL}/auth/register`, jsonData)
      .then((res) => {
        console.log(res)
        if (res.status === 200) {
          toast({
            variant: "primary",
            description: "Successfully created user",
          });
          // localStorage.setItem("edu_exchange_access_token", res.data.token);
        }
      })
      .catch((err) => {
        toast({
          variant: "destructive",
          description: err.message,
        });
      });
  };

  return (
    <>
      <div
        className="flex align-middle flex-1 flex-col justify-center px-6 py-12 lg:px-8 w-screen h-screen"
        style={{ backgroundImage: `url("bgimg.png")` }}
      >
        <div className=" ">
          <div className=" sm:mx-auto sm:w-full sm:max-w-sm shadow-lg px-10  py-8 rounded-xl bg-white">
            <div className="sm:mx-auto sm:w-full sm:max-w-sm ">
              <h2
                className={`text-center text-3xl font-bold leading-9 tracking-tight text-gray-900 ${dm_sans.className}`}
              >
                Registration
              </h2>
              <p
                className={`px-8 mt-3 mb-5 text-sm text-center font-[500] ${montserrat.className}`}
              >
                Fill in the details of the user to get started!!
              </p>
            </div>
            <form className="space-y-6" onSubmit={handleFormSubmit}>
              <InputWithLabel
                id="fullname"
                type="text"
                label="Fullname"
                className={inputClassname}
                name="fullname"
                htmlFor="fullname"
                placeholder="John Doe"
                required
              />

              <InputWithLabel
                id="email"
                type="email"
                label="Your email"
                className={inputClassname}
                autoComplete="email"
                name="email"
                htmlFor="email"
                placeholder="email@company.com"
                required
              />
              <InputWithLabel
                id="role"
                type="text"
                label="Set the user role"
                className={inputClassname}
                name="role"
                htmlFor="role"
                placeholder="TEACHER | STUDENT"
                required
              />

              {/* <Popover open={open} onOpenChange={setOpen}>
                <PopoverTrigger asChild>
                  <Button2
                    variant="outline"
                    role="combobox"
                    aria-expanded={open}
                    className="w-full justify-between"
                  >
                    {value
                      ? roles.find((role) => role.value === value)?.label
                      : "New"}
                    <ChevronsUpDown className="ml-2 h-4 w-4 shrink-0 opacity-50" />
                  </Button2>
                </PopoverTrigger>
                <PopoverContent className="w-full p-0">
                  <Command>
                    <CommandInput placeholder="Search user..." />
                    <CommandEmpty>Not found.</CommandEmpty>
                    <CommandGroup>
                      {roles?.map((role) => (
                        <CommandItem
                          key={role.label}
                          value={role.value}
                          onSelect={(currentValue) => {
                            setValue(
                              currentValue === value ? "" : currentValue
                            );
                            setOpen(false);
                          }}
                        >
                          <Check
                            className={cn(
                              "mr-2 h-4 w-4",
                              value === role.value ? "opacity-100" : "opacity-0"
                            )}
                          />
                          {role.value}
                        </CommandItem>
                      ))}
                    </CommandGroup>
                  </Command>
                </PopoverContent>
              </Popover> */}

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
                text="Register"
                className="flex w-full justify-center fursor-pointer py-2 px-6 rounded-md bg-custom-orange text-sm font-black text-white"
                type="submit"
              />
            </form>

            <p className="mt-5 text-center text-sm text-gray-500">
              Already have account?{" "}
              <Link
                href="/login"
                className="font-semibold text-black-500 hover:text-slate-900"
              >
                Sign in
              </Link>
            </p>
          </div>
        </div>
      </div>
    </>
  );
}
