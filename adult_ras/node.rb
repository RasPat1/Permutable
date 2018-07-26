class Node
  attr_accessor :value, :map, :children, :memo, :memo_key, :perms

  def self.build(num)
    map = {}

    num.to_s.split('').each do |char|
      map[char] = map[char].nil? ? 1 : map[char] + 1
    end

    @@memo = {}

    new(map, nil)
  end

  def initialize(map, value)
    @value = value
    @map = map
    @children = build_children(map)
    @memo_key = to_key(map, value)
    @perms = to_perms.map(&:to_i)
    @@memo[@memo_key] = @perms
  end

  def to_perms
    return @perms unless @perms.nil?

    values = []
    @@memo[@memo_key] = [value] if children.size == 0

    return @@memo[@memo_key] if @@memo.key?(@memo_key)

    children.each do |child|
      values += child.to_perms.map { |perm| "#{perm}#{value}" }
    end

    @@memo[@memo_key] = values.uniq
  end

  private

  def build_children(map)
    nodes = []

    return nodes if map.keys.size == 0

    map.keys.each do |key|
      child_map = map.clone

      if child_map[key] == 1
        child_map.delete(key)
      else
        child_map[key] = child_map[key] - 1
      end

      nodes << Node.new(child_map, key)
    end

    nodes
  end

  def to_key(map, value)
    if value.nil?
      map.hash
    else
      "#{map.hash}_#{value}"
    end
  end
end