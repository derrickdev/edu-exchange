import React from "react";
import Image from "next/image";
import Button from "./Button";
import Counter from "./Counter";
import { motion } from "framer-motion";

const SectionContainer = ({
  title,
  description,
  mockupImg,
  button,
  reversed,
  counter,
}) => {
  return (
    <div className={`flex justify-center items-center py-20`}>
      <motion.div
        className={`flex flex-col sm:flex-row justify-center items-center gap-4 opacity-0 ${
          reversed && "sm:flex-row-reverse"
        }`}
        whileInView={{ opacity: 1, y: 0 }}
        animate={{ y: 100, opacity: 1 }}
        transition={{ duration: 0.5, delayChildren: 0.5 }}
      >
        <div
          className={`flex flex-1 w-full ${reversed ? "justify-end" : null}`}
        >
          <div className="flex flex-col gap-4">
            <h2
              className="text-4xl font-bold leading-[2.8rem] text-black-primary"
              dangerouslySetInnerHTML={{ __html: title }}
            ></h2>
            <p className="text-light-primary text-lg opacity-80 leading-relaxed mb-4">
              {description}
            </p>
            <div>
              <Button
                text={button?.text}
                className={button?.classes}
                startIcon={button?.startIcon}
                endIcon={button?.endIcon}
                to={button?.to}
              />
              {counter && (
                <div className="flex items-center gap-4">
                  {counter.map((e, i) => (
                    <Counter
                      key={`counter-${i}`}
                      count={e.count}
                      text={e.text}
                    />
                  ))}
                </div>
              )}
            </div>
          </div>
        </div>
        <div
          className={`flex items-center flex-1 w-full ${
            reversed ? "justify-start" : "justify-end"
          }`}
        >
          <div className="sm:w-[80%]">
            <Image src={mockupImg} width="100%" height="100%" alt={mockupImg} />
          </div>
        </div>
      </motion.div>
    </div>
  );
};

export default SectionContainer;
