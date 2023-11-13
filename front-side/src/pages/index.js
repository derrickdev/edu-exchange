import LandingPageNavbar from "@/components/LandingPageNavbar";
import LandingPageFooter from "@/components/LandingPageFooter";
import Banner from "@/components/Banner";
import SectionContainer from "@/components/SectionContainer";
import Features from "@/components/Features";
import { learn, message_1, message_2 } from "@/assets/images";
import Cta from "@/components/Cta";


const buttonAttributes = {
  text: "Start chatting now",
  classes:
    "cursor-pointer py-4 px-6 rounded-md text-white bg-custom-orange text-sm",
  to: "/chat"
};

const buttonAttributes2 = {
  text: "See latests post",
  classes:
    "cursor-pointer py-4 px-6 rounded-md text-white bg-custom-orange text-sm",
  to: "/posts"
};

const counterAttributtes = [
  {
    count: "4.3K+",
    text: "Website <br /> Powering",
  },
  {
    count: "7M+",
    text: "Chats <br /> in last 2022",
  },
];

export default function Home() {
  return (
    <div className="scroll-smooth">
      <LandingPageNavbar />
      <main>
        <div className="sm:px-40 px-8">
          <Banner />
        </div>

        <div className="sm:px-40 px-8" id="features">
          <Features />
        </div>

        <div className="sm:px-40 px-8 bg-bg-light" id="about">
          <SectionContainer
            title="Collaborate and <br /> share organizationnal knowledge"
            description="Our platform streamlines the exchange of knowledge, fostering innovation, and enhancing productivity across your organization."
            mockupImg={learn}
          />
        </div>

        <div className="sm:px-40 px-8">
          <SectionContainer
            title="Real-time chatting <br /> between students<br /> and teachers"
            description="Enhance the educational experience with instant communication. Our app enables real-time chatting between teachers and classmates, promoting interactive discussions, quick question-and-answer sessions, and personalized support."
            button={buttonAttributes}
            mockupImg={message_2}
            reversed
          />
        </div>

        <div className="sm:px-40 px-8 bg-bg-light">
          <SectionContainer
            title="Knowledge Exchange and Q&A Forums"
            description="Ask questions, get feedbacks and answers from your peers and teachers. Contribute to your community to improve and ease the learning cursus for your classmates and yourself."
            mockupImg={message_1}
            button={buttonAttributes2}
            // counter={counterAttributtes}
          />
        </div>

         {/* <Testimonials /> */}

        <div className="sm:px-40 px-8">
          <Cta />
        </div>
      </main>
      <LandingPageFooter />
    </div>
  );
}

Home.getLayout = ((page)=> page);