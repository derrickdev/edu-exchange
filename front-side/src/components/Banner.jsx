import React from "react";
import { bannerImg, people } from "@/assets/images";
import Button from "./Button";
import Image from "next/image";
import { ArrowRight, StarIcon } from "lucide-react";
import { montserrat, dm_sans } from "@/utils/fonts";

const Banner = () => {
  return (
    <div className="flex flex-col md:flex-row">
      <div className="flex flex-col flex-1 gap-8 justify-center py-16">
        <h2
          className={`text-4xl sm:text-5xl sm:leading-[3.8rem] font-bold text-black-primary ${montserrat.className}`}
        >
          Let's build a better educational future together.
        </h2>
        <p
          className={`sm:w-[80%] leading-relaxed text-light-primary text-lg opacity-80 ${dm_sans.className}`}
        >
          Our goal is to provide a virtual space where knowledge is shared,
          questions find quick answers, and learning becomes a collaborative
          experience.
        </p>
        <div className="mb-4">
          <Button
            text="Start chatting now"
            className="cursor-pointer py-4 px-6 rounded-md text-white bg-custom-orange text-sm flex items-center gap-2"
            endIcon={<ArrowRight />}
            to={"/chat"}
          />
        </div>
        {/* <div className="flex gap-4 items-center">
          <div>
            <Image src={people} alt="people" width="100%" height="100%" />
          </div>
          <div className="flex items-center">
            <div className="px-4 border-r-2 border-r-slate-600">
              <p className="text-2xl font-bold">2,291</p>
              <p className="text-light-primary text-sm">Happy customers</p>
            </div>

            <div className="px-4">
              <p className="text-2xl font-bold">4.8/5</p>
              <p className="text-light-primary flex items-center">
                <StarIcon size={15} color="#FB8E0B" fill="#FB8E0B" />
                <StarIcon size={15} color="#FB8E0B" fill="#FB8E0B" />
                <StarIcon size={15} color="#FB8E0B" fill="#FB8E0B" />
                <StarIcon size={15} color="#FB8E0B" fill="#FB8E0B" />
                <StarIcon size={15} />
                <span className="ml-2 text-sm">Ratings</span>
              </p>
            </div>
          </div>
        </div> */}
      </div>
      <div className="flex flex-1 items-center w-full">
        <Image src={bannerImg} width="100%" height="100%" alt="hero image" />
      </div>
    </div>
  );
};

export default Banner;
