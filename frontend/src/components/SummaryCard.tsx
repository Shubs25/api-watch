type SummaryCardProps = {
  title: string
  value: number
  description: string
  accent: 'slate' | 'emerald' | 'rose'
}

function SummaryCard({ title, value, description, accent }: SummaryCardProps) {

  const accentStyles = {
    slate: 'text-slate-300 bg-slate-700/40',
    emerald: 'text-emerald-400 bg-emerald-500/10',
    rose: 'text-rose-400 bg-rose-500/10',
  }

  return (
    <div className="bg-slate-900 border border-slate-800 rounded-xl p-6">
      <div className="flex items-center justify-between">
        <p className="text-sm text-slate-400">{title}</p>

        <span className={`px-3 py-1 rounded-lg text-xs font-medium ${accentStyles[accent]}`}>
          {title}
        </span>
      </div>

      <p className="text-3xl font-semibold text-white mt-5">
        {value}
      </p>

      <p className="text-sm text-slate-500 mt-2">
        {description}
      </p>
    </div>
  )
}

export default SummaryCard