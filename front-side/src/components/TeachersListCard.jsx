import Link from "next/link";

const TeachersListCard = () => {
  return (
    <div>
      <div className="flex flex-col w-full bg-white rounded-sm border border-slate-300 min-h-[100px]">
        <div className="w-full p-3 bg-slate-900 text-sm text-white font-semibold flex items-center rounded-t-sm">
          Teachers
        </div>
        <ul className="p-4 flex flex-col gap-2">
          <li>
            <Link
              href="/chat"
              className="inline-flex items-center font-medium text-blue-600 dark:text-blue-500 hover:underline"
            >
              Tiburce Sotohoun
            </Link>
          </li>
          <li>
            <Link
              href="/chat"
              className="inline-flex items-center font-medium text-blue-600 dark:text-blue-500 hover:underline"
            >
              Tiburce Sotohoun
            </Link>
          </li>
          <li>
            <Link
              href="/chat"
              className="inline-flex items-center font-medium text-blue-600 dark:text-blue-500 hover:underline"
            >
              Tiburce Sotohoun
            </Link>
          </li>
          <li>
            <Link
              href="/chat"
              className="inline-flex items-center font-medium text-blue-600 dark:text-blue-500 hover:underline"
            >
              Tiburce Sotohoun
            </Link>
          </li>
          <li>
            <Link
              href="/chat"
              className="inline-flex items-center font-medium text-blue-600 dark:text-blue-500 hover:underline"
            >
              Tiburce Sotohoun
            </Link>
          </li>
          <li>
            <Link
              href="/chat"
              className="inline-flex items-center font-medium text-blue-600 dark:text-blue-500 hover:underline"
            >
              Tiburce Sotohoun
            </Link>
          </li>
        </ul>
      </div>
    </div>
  );
};

export default TeachersListCard;
