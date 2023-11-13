import React from "react";
import Link from "next/link";
import { dm_sans } from "@/utils/fonts";

const Button = ({ text, startIcon, endIcon, className, to, type }) => {
  return (
    <button className={`${dm_sans.className} ${className}`} type={type || "button"}>
      {startIcon}
      {to ? <Link href={to}>{text}</Link> : text} {endIcon}
    </button>
  );
};

export default Button;
