import React from "react";
import FeatureCard from "./FeatureCard";
import { montserrat } from "@/utils/fonts";

const features = [
  {
    title: "Ask Questions",
    icon: "help-circle",
    desc: "Don't understand a concept? Have a problem? Ask, and you'll receive quick responses from your peers or teachers.",
    bg: "bg-orange-100",
    color: "text-orange-400"
  },
  {
    title: "Share Your Knowledge",
    icon: "book-open",
    desc: "Showcase your expertise by helping your classmates, rating the most helpful answers, and building your reputation as an active learner.",
    bg: "bg-green-100",
    color: "text-green-400"
  },
  {
    title: "Learn Together",
    icon: "graduation-cap",
    desc: "Collaborate on exercises, share resources, and develop your skills through a knowledge-sharing community.",
    bg: "bg-orange-100",
    color: "text-orange-400"
  },
];

const Features = () => {
  return (
    <div className="py-20 flex flex-col justify-center items-center gap-12 text-center sm:text-left">
      <div className="flex flex-col flex-1 items-center justify-center gap-4">
        <h2
          className={`text-4xl font-bold text-black-primary capitalize ${montserrat.className}`}
        >
          what you can expect from our platform
        </h2>
        <p className="sm:w-[70%] text-md text-light-primary text-center">
          We understand the challenges students and teachers face, which is why
          we created EduExchange.
        </p>
      </div>

      <div className="flex justify-between items-center flex-wrap gap-4">
        {features.map((e, i) => (
          <FeatureCard
            key={`${e.title.split(" ")[0]}-${i}`}
            title={e.title}
            description={e.desc}
            icon={e.icon}
            bg={e.bg}
            color={e.color}
          />
        ))}
      </div>
    </div>
  );
};

export default Features;
