import dynamic from 'next/dynamic';
import dynamicIconImports from 'lucide-react/dynamicIconImports';


const Icon = ({ name, ...props }) => {
  const LucideIcon = dynamic(dynamicIconImports[name])

  return <LucideIcon {...props} />;
};

export default Icon;