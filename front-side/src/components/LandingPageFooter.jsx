import React from "react";
import Link from "next/link";
import { Facebook, Instagram, Twitter, Linkedin } from "lucide-react";
import { dm_sans } from "@/utils/fonts";

const LandingPageFooter = () => {
  return (
    <div className={`flex sm:px-40 px-16 ${dm_sans.className}`}>
      <div className="flex flex-col sm:flex-row gap-4 flex-1 justify-between items-center py-4">
        <div>edu.Exchange</div>
        <p className="text-light-primary text-sm font-light">
          © Copyright 2023 All Rights Reserved
        </p>
        <div className="flex gap-4 items-center">
          <Facebook />
          <Instagram />
          <Linkedin />
          <Twitter />
        </div>
      </div>
    </div>
  );
};

export default LandingPageFooter;
